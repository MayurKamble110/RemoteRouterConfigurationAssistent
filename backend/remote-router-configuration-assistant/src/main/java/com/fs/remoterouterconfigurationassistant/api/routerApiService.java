package com.fs.remoterouterconfigurationassistant.api;

import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class routerApiService {

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

            return false;
        }

        return true;
    }


    public String executeCommandOnRouter(String command) {
        StringBuilder output = new StringBuilder();
        System.out.println(session);
        try {
            if (session != null && session.isConnected()) {
                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                channelExec.setCommand(command);
                channelExec.setErrStream(System.err);

                InputStream in = channelExec.getInputStream();
                channelExec.connect();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    output.append(new String(buffer, 0, bytesRead));
                }

                channelExec.disconnect();
            } else {
                output.append("SSH session is not established.");
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
}
