package com.fs.remoterouterconfigurationassistant.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.CpuProcessHistoryDto;
import com.fs.remoterouterconfigurationassistant.api.model.InterfaceData;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.RouterCommandInterpreter;
import com.fs.remoterouterconfigurationassistant.auth.dao.UserRepository;
import com.fs.remoterouterconfigurationassistant.auth.entities.User;
import com.fs.remoterouterconfigurationassistant.databases.DeviceInterfaceRepository;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.apache.catalina.core.ApplicationContext;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerError;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RouterApiService {

    private Session session;
    @Autowired
    RouterCommandInterpreter routerCommandInterpreter;

    @Autowired
    NetworkDeviceRepositoryService networkDeviceRepositoryService;

    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    @Autowired
    DeviceInterfaceRepository deviceInterfaceRepository;

    @Autowired
    UserRepository userRepository;

    public boolean connectToRouter(RouterAccessDetails accessDetails) {
        String username = accessDetails.getUsername();
        String password = accessDetails.getPassword();
        String hostname = accessDetails.getHostIp();
        int port = 22; // Default SSH port
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, hostname, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public String executeCommandOnRouter(CommandRequest commandRequest)
                    throws ResourceAccessException, IOException, JSchException {
        StringBuilder output = new StringBuilder();
        try {
            if (session != null && session.isConnected()) {
                Channel channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand(commandRequest.getCommand());
                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);
                InputStream in = channel.getInputStream();
                channel.connect();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    output.append(new String(buffer, 0, bytesRead));
                }
                channel.disconnect();

               routerCommandInterpreter.processor(output, commandRequest);

            } else {
                System.out.println("SSH session is not established.");
            }

        } catch ( Exception e) {
            e.printStackTrace();
            output.append("Error executing command: ").append(e.getMessage());
            throw e;
        }

        return output.toString();
    }

    public String disconnectSSH() {
        if (session == null) {
            return "CONNECTION NOT PRESENT";
        } else if (session != null && session.isConnected()) {
            session.disconnect();
            return "DISCONNECTED FROM ROUTER";
        }

        return "FAILED TO DISCONNECT FROM ROUTER";
    }

    public void addNewNetworkDevice(NewDevice newDevice) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        User user = userRepository.getReferenceById(email);
        System.out.println(user);
        NetworkDeviceDao dao = NetworkDeviceDao.builder()
                        .deviceName(newDevice.getDeviceName())
                        .ipAddress(newDevice.getIpAddress())
                        .username(newDevice.getUsername())
                        .password(newDevice.getPassword())
                        .enablePassword(newDevice.getEnablePassword())
                        .user(user)
                        .build();

        networkDeviceRepositoryService.addNetworkDeviceToDatabase(dao);

    }

    public List<NetworkDeviceDao> getAllNetworkDevices() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        return networkDeviceRepository.findAllRoutersByUserAccount(email);
    }

    public InterfaceData getInterfacesByDeviceId(long deviceId) {
        List<DeviceInterfaceDao> interfaces = new ArrayList<>();
        List<DeviceInterfaceDao> in = deviceInterfaceRepository.findAll();
        for (DeviceInterfaceDao iface : in) {
            if (iface.getNetworkDeviceDao().getDeviceId() == deviceId)
                interfaces.add(iface);
        }
        Collections.sort(interfaces, new Comparator<DeviceInterfaceDao>() {
            @Override
            public int compare(DeviceInterfaceDao d1, DeviceInterfaceDao d2) {
                String[] parts1 = d1.getInterfaceName().split("/");
                String[] parts2 = d2.getInterfaceName().split("/");
                int num1 = Integer.parseInt(parts1[1]);
                int num2 = Integer.parseInt(parts2[1]);
                return Integer.compare(num1, num2);
            }
        });
        InterfaceData interfaceData = new InterfaceData();
        interfaceData.setInterfaces(interfaces);
        int upInterfaceCount = deviceInterfaceRepository.getUpInterfaceCount(deviceId);
        int downInterfaceCount = deviceInterfaceRepository.getDownInterfaceCount(deviceId);

        interfaceData.setDownInterfaceCount(downInterfaceCount);
        interfaceData.setUpInterfaceCount(upInterfaceCount);
        return interfaceData;

    }

    public String analyseRouter(Long deviceId) throws ResourceAccessException, BadRequestException {
        String rawOutput = FlaskServer.analyseRouter(deviceId);
        return rawOutput.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
    }

    public Map<String, CpuProcessHistoryDto> getCpuProcessHistory(Long deviceId)
                    throws JsonProcessingException, BadRequestException {
        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceId);

        if (networkDeviceDao.isPresent()) {
            String cpuProcessHistory = networkDeviceDao.get().getParsedCpuProcessesHistory();
            if (cpuProcessHistory == null)
                throw new ResourceAccessException("CPU history content is null.");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, CpuProcessHistoryDto> cpuDataMap =
                            objectMapper.readValue(cpuProcessHistory, Map.class);
            System.out.println(cpuDataMap);

            return cpuDataMap;
        }
        throw new BadRequestException("Requested resource not found.");
    }
}
