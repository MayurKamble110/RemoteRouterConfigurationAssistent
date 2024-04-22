package com.fs.remoterouterconfigurationassistant.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.CpuProcessHistoryDto;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.RouterCommandInterpreter;
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

    @Autowired
    DeviceInterfaceRepository deviceInterfaceRepository;
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

    public String executeCommandOnRouter(CommandRequest commandRequest) throws ResourceAccessException,JsonProcessingException,BadRequestException{
        StringBuilder output = new StringBuilder();
        output.append("!\n" +
                "! Last configuration change at 22:31:52 UTC Wed Apr 17 2024 by admin\n" +
                "! NVRAM config last updated at 18:29:37 UTC Tue Apr 9 2024 by admin\n" +
                "!\n" +
                "version 15.2\n" +
                "no service pad\n" +
                "service timestamps debug datetime msec\n" +
                "service timestamps log datetime msec localtime show-timezone\n" +
                "no service password-encryption\n" +
                "!\n" +
                "hostname Cisco_3560CX\n" +
                "!\n" +
                "boot-start-marker\n" +
                "boot-end-marker\n" +
                "!\n" +
                "!\n" +
                "logging console informational\n" +
                "enable password aristo\n" +
                "!\n" +
                "username admin password 0 aristo\n" +
                "no aaa new-model\n" +
                "system mtu routing 1500\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "ip routing\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "ip domain-name txqalab.forescout.local\n" +
                "ipv6 unicast-routing\n" +
                "vtp mode off\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "spanning-tree mode rapid-pvst\n" +
                "spanning-tree extend system-id\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "vlan internal allocation policy ascending\n" +
                "!\n" +
                "vlan 20\n" +
                "name Test\n" +
                "!\n" +
                "vlan 128,1687-1688\n" +
                "!\n" +
                "lldp run\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "!\n" +
                "interface GigabitEthernet0/1\n" +
                "description AutomationTestingPort\n" +
                "switchport access vlan 1687\n" +
                "switchport mode access\n" +
                "snmp trap mac-notification change added\n" +
                "snmp trap mac-notification change removed\n" +
                "spanning-tree portfast edge\n" +
                "!\n" +
                "interface GigabitEthernet0/2\n" +
                "description Automation2\n" +
                "switchport access vlan 1688\n" +
                "switchport mode access\n" +
                "snmp trap mac-notification change added\n" +
                "snmp trap mac-notification change removed\n" +
                "spanning-tree portfast edge\n" +
                "!\n" +
                "interface GigabitEthernet0/3\n" +
                "switchport access vlan 1687\n" +
                "switchport mode access\n" +
                "!\n" +
                "interface GigabitEthernet0/4\n" +
                "switchport access vlan 1687\n" +
                "snmp trap mac-notification change added\n" +
                "!\n" +
                "interface GigabitEthernet0/5\n" +
                "switchport access vlan 1687\n" +
                "snmp trap mac-notification change added\n" +
                "!\n" +
                "interface GigabitEthernet0/6\n" +
                "switchport access vlan 1687\n" +
                "snmp trap mac-notification change added\n" +
                "!\n" +
                "interface GigabitEthernet0/7\n" +
                "switchport access vlan 1687\n" +
                "!\n" +
                "interface GigabitEthernet0/8\n" +
                "description Mgmt\n" +
                "switchport trunk native vlan 128\n" +
                "switchport mode trunk\n" +
                "snmp trap mac-notification change added\n" +
                "!\n" +
                "interface GigabitEthernet0/9\n" +
                "switchport access vlan 1687\n" +
                "!\n" +
                "interface GigabitEthernet0/10\n" +
                "switchport access vlan 1687\n" +
                "!\n" +
                "interface GigabitEthernet0/11\n" +
                "switchport access vlan 1687\n" +
                "!\n" +
                "interface GigabitEthernet0/12\n" +
                "switchport access vlan 1687\n" +
                "!\n" +
                "interface Vlan1\n" +
                "no ip address\n" +
                "shutdown\n" +
                "!\n" +
                "interface Vlan128\n" +
                "ip address 10.16.128.34 255.255.255.0\n" +
                "ipv6 address FD6F:9D5E:221E:128::34/64\n" +
                "!\n" +
                "interface Vlan1687\n" +
                "ip address 10.16.177.123 255.255.255.192\n" +
                "!\n" +
                "interface Vlan1688\n" +
                "ip address 10.16.178.124 255.255.255.192\n" +
                "!\n" +
                "ip default-gateway 10.16.128.1\n" +
                "ip forward-protocol nd\n" +
                "!\n" +
                "ip http server\n" +
                "ip http secure-server\n" +
                "!\n" +
                "ip route 0.0.0.0 0.0.0.0 10.16.128.1\n" +
                "ip ssh version 2\n" +
                "!\n" +
                "!\n" +
                "logging host 10.100.97.65\n" +
                "ipv6 route ::/0 FD6F:9D5E:221E:128::2\n" +
                "!\n" +
                "!\n" +
                "snmp-server group group3 v3 auth write v1default\n" +
                "snmp-server group v3user v3 priv\n" +
                "snmp-server group authpriv v3 auth write vldefault notify vldefault\n" +
                "snmp-server group authpriv v3 priv write v1default notify v1default\n" +
                "snmp-server group authpriv v3 auth context vlan match prefix\n" +
                "snmp-server view everything iso included\n" +
                "snmp-server view everything internet included\n" +
                "snmp-server view everything mib-2 included\n" +
                "snmp-server view everything system included\n" +
                "snmp-server view everything interfaces included\n" +
                "snmp-server community aristo RW\n" +
                "snmp-server enable traps snmp authentication linkdown linkup coldstart warmstart\n" +
                "snmp-server enable traps mac-notification change move threshold\n" +
                "snmp-server host 10.100.97.65 version 2c aristo\n" +
                "snmp-server host 10.100.97.66 aristo\n" +
                "snmp-server host 10.16.163.101 version 2c aristo\n" +
                "snmp-server host 10.16.177.68 version 2c aristo\n" +
                "snmp-server host 10.16.177.78 version 2c aristo\n" +
                "snmp-server host 10.100.97.67 version 3 priv snmpv3\n" +
                "snmp-server host 10.16.177.68 version 3 priv snmpv3\n" +
                "snmp-server host 10.16.149.224 version 3 auth user3\n" +
                "snmp-server host 10.100.97.65 version 3 priv v3user\n" +
                "no vstack\n" +
                "!\n" +
                "line con 0\n" +
                "line vty 0 4\n" +
                "exec-timeout 5 0\n" +
                "password aristo\n" +
                "login local\n" +
                "transport input ssh\n" +
                "line vty 5 15\n" +
                "exec-timeout 5 0\n" +
                "password aristo\n" +
                "login local\n" +
                "transport input ssh\n" +
                "!\n" +
                "mac address-table notification change\n" +
                "!\n" +
                "end");
        try {
//            if (session != null && session.isConnected()) {
//                Channel channel = session.openChannel("exec");
//                ((ChannelExec) channel).setCommand(commandRequest.getCommand());
//                channel.setInputStream(null);
//                ((ChannelExec) channel).setErrStream(System.err);
//                InputStream in = channel.getInputStream();
//                channel.connect();
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//
//                while ((bytesRead = in.read(buffer)) != -1) {
//                    output.append(new String(buffer, 0, bytesRead));
//                }
//                channel.disconnect();
//
//                routerCommandInterpreter.processor(output, commandRequest);
//
//            } else {
//                System.out.println("SSH session is not established.");
//            }
            routerCommandInterpreter.processor(output,commandRequest);
        } catch ( IOException e) {
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

    public List<DeviceInterfaceDao> getInterfacesByDeviceId(long deviceId) {
        List<DeviceInterfaceDao> interfaces = new ArrayList<>();
        return deviceInterfaceRepository.getInterfaceBySortedOrder(deviceId);
    }

    public String analyseRouter(Long deviceId) throws ResourceAccessException, BadRequestException {
        String rawOutput = FlaskServer.analyseRouter(deviceId);
        return rawOutput.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
    }

    public Map<String, CpuProcessHistoryDto> getCpuProcessHistory(Long deviceId) throws JsonProcessingException, BadRequestException {
        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceId);

        if(networkDeviceDao.isPresent())
        {
            String cpuProcessHistory = networkDeviceDao.get().getParsedCpuProcessesHistory();
            if(cpuProcessHistory == null)
                throw new ResourceAccessException("CPU history content is null.");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, CpuProcessHistoryDto> cpuDataMap = objectMapper.readValue(cpuProcessHistory, Map.class);
            System.out.println(cpuDataMap);

            return cpuDataMap;
        }
        throw new BadRequestException("Requested resource not found.");
    }
}
