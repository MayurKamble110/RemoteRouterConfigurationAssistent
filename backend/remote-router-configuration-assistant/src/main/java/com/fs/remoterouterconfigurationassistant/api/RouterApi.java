package com.fs.remoterouterconfigurationassistant.api;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RouterApi {

    @Autowired
    RouterApiService routerApiService;

    @Autowired
    ShowInterfaceRepositoryService service;

    @PostMapping(path = "/connect")
    public String makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails) {
        boolean response = routerApiService.connectToRouter(accessDetails);

        return response ? "1" : "0";
    }

    @PostMapping(path = "/commands")
    public String executeCommandOnRouter(@RequestBody String command) {

        String response = routerApiService.executeCommandOnRouter(command);

        System.out.println(response);

        return response;
    }


    @PostMapping(path = "/disconnect")
    public String disconnectFromRouter() {

        return routerApiService.disconnectSSH();
    }

    @PostMapping(path = "/add-device")
    public String addDevice(@RequestBody NewDevice newDevice)
    {
        routerApiService.addNewNetworkDevice(newDevice);

        return "1";
    }
    @PostMapping("/test")
    public String test()
    {
        service.addInterfacesToDatabase("");
        return "Hi";
    }

}
