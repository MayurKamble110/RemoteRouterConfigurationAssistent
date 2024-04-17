package com.fs.remoterouterconfigurationassistant.api;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.databases.DeviceInterfaceRepository;
import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/routers")
public class RouterApi {

    @Autowired
    RouterApiService routerApiService;

    @Autowired
    ShowInterfaceRepositoryService service;

    @Autowired
    DeviceInterfaceRepository deviceInterfaceRepository;

    @PostMapping(path = "/connect")
    public String makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails) {
        boolean response = routerApiService.connectToRouter(accessDetails);

        return response ? "1" : "0";
    }

    @PostMapping(path = "/commands")
    public ResponseEntity<?> executeCommandOnRouter(@RequestBody CommandRequest commandRequest) {
        try {
            String response = routerApiService.executeCommandOnRouter(commandRequest);
            return ResponseEntity.ok(response);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (ResourceAccessException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @PostMapping(path = "/disconnect")
    public String disconnectFromRouter() {

        return routerApiService.disconnectSSH();
    }

    @PostMapping(path = "")
    public String addDevice(@RequestBody NewDevice newDevice) {
        routerApiService.addNewNetworkDevice(newDevice);

        return "1";
    }

    @GetMapping(path = "")
    public List<NetworkDeviceDao> getAllRouterData() {
        List<NetworkDeviceDao> networkDeviceDaoList = routerApiService.getAllNetworkDevices();
        System.out.println(networkDeviceDaoList);

        return networkDeviceDaoList;

    }

    @GetMapping(path = "/{deviceId}/interfaces")
    public List<DeviceInterfaceDao> getInterfaceData(@PathVariable Long deviceId){
        List<DeviceInterfaceDao> deviceInterfaceDaoList = routerApiService.getInterfacesByDeviceId(deviceId);
        return deviceInterfaceDaoList;
    }

    @GetMapping(path = "/{deviceId}/analyse")
    public ResponseEntity<?> analyseRouter(@PathVariable Long deviceId) {
        try {
            return ResponseEntity.ok(routerApiService.analyseRouter(deviceId));
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{deviceId}/cpu-process-history")
    public ResponseEntity<?> getCpuProcessHistoryData(@PathVariable Long deviceId){
        try {
            return ResponseEntity.ok(routerApiService.getCpuProcessHistory(deviceId));
        }catch (JsonProcessingException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (ResourceAccessException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

//    @PostMapping("/test")
//    public String test()
//    {
//        service.addInterfacesToDatabase("",);
//        return "Hi";
//    }

}
