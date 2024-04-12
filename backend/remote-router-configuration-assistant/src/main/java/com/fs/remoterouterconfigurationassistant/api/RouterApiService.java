package com.fs.remoterouterconfigurationassistant.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.CpuProcessHistoryDto;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.RouterCommandInterpreter;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerError;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String executeCommandOnRouter(CommandRequest commandRequest) {
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
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            output.append("Error executing command: ").append(e.getMessage());
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

    public void addNewNetworkDevice(NewDevice newDevice)
    {
        NetworkDeviceDao dao = NetworkDeviceDao.builder()
                        .deviceName(newDevice.getDeviceName())
                        .ipAddress(newDevice.getIpAddress())
                        .username(newDevice.getUsername())
                        .password(newDevice.getPassword())
                        .enablePassword(newDevice.getEnablePassword())
                        .build();

        networkDeviceRepositoryService.addNetworkDeviceToDatabase(dao);

    }

    public List<NetworkDeviceDao> getAllNetworkDevices()
    {
        return networkDeviceRepository.findAll();
    }

    public String analyseRouter(Long deviceId) throws ResourceAccessException, BadRequestException {
        return FlaskServer.analyseRouter(deviceId);
    }

    public Map<String, CpuProcessHistoryDto> getCpuProcessHistory(Long deviceId) throws JsonProcessingException {
        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceId);

        if(networkDeviceDao.isPresent())
        {
            String cpuProcessHistory = networkDeviceDao.get().getParsedCpuProcessesHistory();

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, CpuProcessHistoryDto> cpuDataMap = objectMapper.readValue(cpuProcessHistory, Map.class);
            System.out.println(cpuDataMap);

            return cpuDataMap;

        }

        return null;

    }
}
