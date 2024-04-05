package com.fs.remoterouterconfigurationassistant.api;

import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.RouterCommandInterpreter;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        StringBuilder output = new StringBuilder("""
                         Cisco IOS Software, C3560CX Software (C3560CX-UNIVERSALK9-M), Version 15.2(4) RELEASE SOFTWARE (fc4)
                Technical Support: http://www.cisco.com/techsupport
                Copyright (c) 1986-2018 by Cisco Systems, Inc.
                Compiled Thu 05-Apr-18 03:17 by prod_rel_team
                                       
                ROM: Bootstrap program is C3560CX boot loader
                BOOTLDR: C3560CX Boot Loader (C3560CX-HBOOT-M) Version 15.2(4r)E5, RELEASE SOARE (fc4)
                                       
                Cisco_3560CX uptime is 4 days, 11 hours, 45 minutes
                System returned to ROM by power-on
                System restarted at 19:53:10 UTC Sat Mar 30 2024
                System image file is "flash:/c3560cx-universalk9-mz.152-4.E6/c3560cx-universa-mz.152-4.E6.bin"
                Last reload reason: Reload command
                                       
                                       
                                       
                This product contains cryptographic features and is subject to United
                States and local country laws governing import, export, transfer and
                use. Delivery of Cisco cryptographic products does not imply
                 """);
//        System.out.println(session);
//        try {
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
//            } else {
//                output.append("SSH session is not established.");
//            }
//        } catch (JSchException | IOException e) {
//            e.printStackTrace();
//            output.append("Error executing command: ").append(e.getMessage());
//        }
//        System.out.println(output);

        routerCommandInterpreter.processor(output, commandRequest);

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
}
