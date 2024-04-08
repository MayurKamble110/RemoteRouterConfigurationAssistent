package com.fs.remoterouterconfigurationassistant.api;

import java.io.Serial;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/routers")
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
    public String executeCommandOnRouter(@RequestBody CommandRequest commandRequest) {

        String response = routerApiService.executeCommandOnRouter(commandRequest);

        System.out.println(response);

        return response;
    }


    @PostMapping(path = "/disconnect")
    public String disconnectFromRouter() {

        return routerApiService.disconnectSSH();
    }

    @PostMapping(path = "")
    public String addDevice(@RequestBody NewDevice newDevice)
    {
        routerApiService.addNewNetworkDevice(newDevice);

        return "1";
    }

    @GetMapping(path = "")
    public List<NetworkDeviceDao>  getAllRouterData()
    {
        List<NetworkDeviceDao>  networkDeviceDaoList = routerApiService.getAllNetworkDevices();
        System.out.println(networkDeviceDaoList);

        return networkDeviceDaoList;

    }

    @GetMapping(path = "/{deviceId}/analyse")
    public ResponseEntity<?> analyseRouter(@PathVariable String deviceId){
        try{
            return ResponseEntity.ok(routerApiService.analyseRouter(deviceId));
        }catch (ResourceAccessException e){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (BadRequestException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping("/test")
//    public String test()
//    {
//        service.addInterfacesToDatabase("",);
//        return "Hi";
//    }

}
