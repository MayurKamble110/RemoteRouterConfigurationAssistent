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

    public StringBuilder connectToRouter(RouterAccessDetails accessDetails)
    {

        String username = accessDetails.getUsername();
        String password = accessDetails.getPassword();
        String hostname = accessDetails.getHostIp();
        int port = 22; // Default SSH port
        StringBuilder ans = new StringBuilder();
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, hostname, port);
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("show interfaces");
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            InputStream in = channelExec.getInputStream();
            channelExec.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
//                    ans.append(Arrays.toString(tmp));
                    ans.append(new String(tmp, 0, i));
                }
                if (channelExec.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channelExec.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channelExec.disconnect();
            session.disconnect();

        } catch (Exception e) {
            System.out.println(e);
            return new StringBuilder("FAILED");
//            e.printStackTrace();
        }
        return ans;
    }
}
