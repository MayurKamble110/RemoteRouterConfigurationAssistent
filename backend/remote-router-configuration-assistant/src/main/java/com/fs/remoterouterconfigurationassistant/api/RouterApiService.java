package com.fs.remoterouterconfigurationassistant.api;

import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class RouterApiService {

    private Session session;

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

    public String executeCommandOnRouter() {
        StringBuilder output = new StringBuilder();
        System.out.println(session);

        try {
            if (session != null && session.isConnected()) {
                Channel channel = session.openChannel("exec");


                    ((ChannelExec)channel).setCommand("show interfaces && show version");


//                channelExec.setCommand(command);
//                channelExec.setErrStream(System.err);

                channel.setInputStream(null);

                ((ChannelExec)channel).setErrStream(System.err);
                InputStream in = channel.getInputStream();
                channel.connect();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    output.append(new String(buffer, 0, bytesRead));
                }

                channel.disconnect();
            } else {
                output.append("SSH session is not established.");
            }
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            output.append("Error executing command: ").append(e.getMessage());
        }
        System.out.println(output);

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
}
