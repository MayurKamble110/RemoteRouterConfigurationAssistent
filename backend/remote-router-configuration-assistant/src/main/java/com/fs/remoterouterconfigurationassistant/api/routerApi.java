package com.fs.remoterouterconfigurationassistant.api;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class routerApi {

    @Autowired
    routerApiService routerApiService;

    @PostMapping(path = "/connect")
    public String makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails) {
        boolean response = routerApiService.connectToRouter(accessDetails);


        return response ? "1" : "0" ;
    }

    @PostMapping(path = "/commands")
    public String executeCommandOnRouter(@RequestBody CommandRequest commandRequest) {

        String response = routerApiService.executeCommandOnRouter(commandRequest.getCommand());

        System.out.println(response);

        return response;
    }


    @PostMapping(path = "/disconnect")
    public String disconnectFromRouter() {
        return routerApiService.disconnectSSH();
    }
}
