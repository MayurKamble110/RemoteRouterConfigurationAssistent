package com.fs.remoterouterconfigurationassistant.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.InterfaceData;
import com.fs.remoterouterconfigurationassistant.api.model.NewDevice;
import com.fs.remoterouterconfigurationassistant.databases.DeviceInterfaceRepository;
import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.jcraft.jsch.JSchException;

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

    @Autowired
    NetworkDeviceRepository networkDeviceRepository;
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
        } catch (JSchException | IOException e) {
            throw new RuntimeException(e);
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
    public InterfaceData getInterfaceData(@PathVariable Long deviceId){
        InterfaceData  interfaceData = routerApiService.getInterfacesByDeviceId(deviceId);
        return interfaceData;
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
    @GetMapping(path = "/{deviceId}/interfaces/count")
    public int getInterfacesCount(@PathVariable Long deviceId)
    {
        return deviceInterfaceRepository.getInterfaceCount(deviceId);
    }

    @GetMapping(path = "/{deviceId}/interfaces/count/up")
    public int getUpInterfacesCount(@PathVariable Long deviceId)
    {
        return deviceInterfaceRepository.getUpInterfaceCount(deviceId);
    }

    @GetMapping(path = "/{deviceId}/interfaces/count/down")
    public int getDownInterfacesCount(@PathVariable Long deviceId)
    {
        return deviceInterfaceRepository.getDownInterfaceCount(deviceId);
    }

    @GetMapping(path = "/{deviceId}/count-access-mode")
    public List<Integer> getCountOfAccessMode(@PathVariable Long deviceId)
    {
        List<Integer> in = new ArrayList<>();
        Integer countAccess =0;
        Integer countTrunk =0;
        InterfaceData interfaceData = routerApiService.getInterfacesByDeviceId(deviceId);
        for(DeviceInterfaceDao deviceInterfaceDao: interfaceData.getInterfaces())
        {
           if(Objects.equals(deviceInterfaceDao.getMode(), "access"))
               countAccess++;
           else
               countTrunk ++;

        }
        in.add(countAccess);
        in.add(countTrunk);
        return in;
    }


//    @PostMapping("/test")
//    public String test()
//    {
//        service.addInterfacesToDatabase("",);
//        return "Hi";
//    }

    @PostMapping("/test/{id}")
    public void test(@PathVariable Long id) {
        String output = """                                               
                                            
                    Current configuration : 3944 bytes
                                            
                    !
                                            
                    ! Last configuration change at 22:31:52 UTC Wed Apr 17 2024 by admin
                                            
                    ! NVRAM config last updated at 18:29:37 UTC Tue Apr 9 2024 by admin
                                            
                    !
                                            
                    version 15.2
                                            
                    no service pad
                                            
                    service timestamps debug datetime msec
                                            
                    service timestamps log datetime msec localtime show-timezone
                                            
                    no service password-encryption
                                            
                    !
                                            
                    hostname Cisco_3560CX
                                            
                    !
                                            
                    boot-start-marker
                                            
                    boot-end-marker
                                            
                    !
                                            
                    !
                                            
                    logging console informational
                                            
                    enable password aristo
                                            
                    !
                                            
                    username admin password 0 aristo
                                            
                    no aaa new-model
                                            
                    system mtu routing 1500
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    ip routing
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    ip domain-name txqalab.forescout.local
                                            
                    ipv6 unicast-routing
                                            
                    vtp mode off
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    spanning-tree mode rapid-pvst
                                            
                    spanning-tree extend system-id
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    vlan internal allocation policy ascending
                                            
                    !
                                            
                    vlan 20
                                            
                    name Test
                                            
                    !
                                            
                    vlan 128,1687-1688
                                            
                    !
                                            
                    lldp run
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    !
                                            
                    interface GigabitEthernet0/1
                                            
                    description AutomationTestingPort
                                            
                    switchport access vlan 1687
                                            
                    switchport mode access
                                            
                    snmp trap mac-notification change added
                                            
                    snmp trap mac-notification change removed
                                            
                    spanning-tree portfast edge
                                            
                    !
                                            
                    interface GigabitEthernet0/2
                                            
                    description Automation2
                                            
                    switchport access vlan 1688
                                            
                    switchport mode access
                                            
                    snmp trap mac-notification change added
                                            
                    snmp trap mac-notification change removed
                                            
                    spanning-tree portfast edge
                                            
                    !
                                            
                    interface GigabitEthernet0/3
                                            
                    switchport access vlan 1687
                                            
                    switchport mode access
                                            
                    !
                                            
                    interface GigabitEthernet0/4
                                            
                    switchport access vlan 1687
                                            
                    snmp trap mac-notification change added
                                            
                    !
                                            
                    interface GigabitEthernet0/5
                                            
                    switchport access vlan 1687
                                            
                    snmp trap mac-notification change added
                                            
                    !
                                            
                    interface GigabitEthernet0/6
                                            
                    switchport access vlan 1687
                                            
                    snmp trap mac-notification change added
                                            
                    !
                                            
                    interface GigabitEthernet0/7
                                            
                    switchport access vlan 1687
                                            
                    !
                                            
                    interface GigabitEthernet0/8
                                            
                    description Mgmt
                                            
                    switchport trunk native vlan 128
                                            
                    switchport mode trunk
                                            
                    snmp trap mac-notification change added
                                            
                    !
                                            
                    interface GigabitEthernet0/9
                                            
                    switchport access vlan 1687
                                            
                    !
                                            
                    interface GigabitEthernet0/10
                                            
                    switchport access vlan 1687
                                            
                    !
                                            
                    interface GigabitEthernet0/11
                                            
                    switchport access vlan 1687
                                            
                    !
                                            
                    interface GigabitEthernet0/12
                                            
                    switchport access vlan 1687
                                            
                    !
                                            
                    interface Vlan1
                                            
                    no ip address
                                            
                    shutdown
                                            
                    !
                                            
                    interface Vlan128
                                            
                    ip address 10.16.128.34 255.255.255.0
                                            
                    ipv6 address FD6F:9D5E:221E:128::34/64
                                            
                    !
                                            
                    interface Vlan1687
                                            
                    ip address 10.16.177.123 255.255.255.192
                                            
                    !
                                            
                    interface Vlan1688
                                            
                    ip address 10.16.178.124 255.255.255.192
                                            
                    !
                                            
                    ip default-gateway 10.16.128.1
                                            
                    ip forward-protocol nd
                                            
                    !
                                            
                    ip http server
                                            
                    ip http secure-server
                                            
                    !
                                            
                    ip route 0.0.0.0 0.0.0.0 10.16.128.1
                                            
                    ip ssh version 2
                                            
                    !
                                            
                    !
                                            
                    logging host 10.100.97.65
                                            
                    ipv6 route ::/0 FD6F:9D5E:221E:128::2
                                            
                    !
                                            
                    !
                                            
                    snmp-server group group3 v3 auth write v1default
                                            
                    snmp-server group v3user v3 priv
                                            
                    snmp-server group authpriv v3 auth write vldefault notify vldefault
                                            
                    snmp-server group authpriv v3 priv write v1default notify v1default
                                            
                    snmp-server group authpriv v3 auth context vlan match prefix
                                            
                    snmp-server view everything iso included
                                            
                    snmp-server view everything internet included
                                            
                    snmp-server view everything mib-2 included
                                            
                    snmp-server view everything system included
                                            
                    snmp-server view everything interfaces included
                                            
                    snmp-server community aristo RW
                                            
                    snmp-server enable traps snmp authentication linkdown linkup coldstart warmstart
                                            
                    snmp-server enable traps mac-notification change move threshold
                                            
                    snmp-server host 10.100.97.65 version 2c aristo
                                            
                    snmp-server host 10.100.97.66 aristo
                                            
                    snmp-server host 10.16.163.101 version 2c aristo
                                            
                    snmp-server host 10.16.177.68 version 2c aristo
                                            
                    snmp-server host 10.16.177.78 version 2c aristo
                                            
                    snmp-server host 10.100.97.67 version 3 priv snmpv3
                                            
                    snmp-server host 10.16.177.68 version 3 priv snmpv3
                                            
                    snmp-server host 10.16.149.224 version 3 auth user3
                                            
                    snmp-server host 10.100.97.65 version 3 priv v3user
                                            
                    no vstack
                                            
                    !
                                            
                    line con 0
                                            
                    line vty 0 4
                                            
                    exec-timeout 5 0
                                            
                    password aristo
                                            
                    login local
                                            
                    transport input ssh
                                            
                    line vty 5 15
                                            
                    exec-timeout 5 0
                                            
                    password aristo
                                            
                    login local
                                            
                    transport input ssh
                                            
                    !
                                            
                    mac address-table notification change
                                            
                    !
                                            
                    end
                                            
                    """;
        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(id);
        if (networkDeviceDao.isPresent()) {
            networkDeviceDao.get().setRawLogs(output);
            networkDeviceRepository.save(networkDeviceDao.get());
        }
    }

}
