package com.fs.remoterouterconfigurationassistant.api;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RouterApi {

    @Autowired
    RouterApiService routerApiService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = "/connect")
    public String makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails) {
        boolean response = routerApiService.connectToRouter(accessDetails);

        return response ? "1" : "0";
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

    @PostMapping(path = "/django")
    public String makeApiCall() {
        String output = """
                        Router# show running-config
                        Building configuration...
                                                
                        Current configuration:
                        !
                        version 15.1
                        service timestamps debug datetime msec
                        service timestamps log datetime msec
                        no service password-encryption
                        !
                        hostname Router
                        !
                        ip domain-name example.com
                        !
                        interface GigabitEthernet0/0
                         ip address 192.168.1.1 255.255.255.0
                         duplex auto
                         speed auto
                        !
                        interface GigabitEthernet0/1
                         ip address 10.0.0.1 255.255.255.0
                         duplex auto
                         speed auto
                        !
                        vlan 10
                         name Sales_VLAN
                        !
                        vlan 20
                         name Engineering_VLAN
                        !
                        spanning-tree mode rapid-pvst
                        !
                        switchport port-security
                         switchport port-security maximum 10
                         switchport port-security violation restrict
                         switchport port-security aging time 2
                        !
                        vtp mode server
                         vtp domain CompanyVLANs
                         vtp password secretpassword
                        !
                        router ospf 1
                         network 192.168.1.0 0.0.0.255 area 0
                         network 10.0.0.0 0.0.0.255 area 0
                        !
                        access-list 101 permit ip 192.168.1.0 0.0.0.255 any
                        !
                        firewall
                          policy 1 permit ip source 192.168.1.0 255.255.255.0 destination any
                        !
                        dhcp pool LAN
                         network 192.168.1.0 255.255.255.0
                         default-router 192.168.1.1
                         dns-server 8.8.8.8
                        !
                        logging 192.168.1.100
                        !
                        snmp-server community public RO
                        !
                        ntp server 1.2.3.4
                        !
                        crypto isakmp policy 1
                         authentication pre-share
                        !
                        crypto ipsec transform-set myset esp-aes 128 esp-sha-hmac
                        !
                        crypto map mymap 10 ipsec-isakmp
                         set peer 5.5.5.5
                         set transform-set myset
                         match address 101
                        !
                        qos
                          policy-map mypolicy
                           class voice
                            priority percent 30
                           class data
                            bandwidth percent 70
                        !
                        ip nat inside source list 1 interface GigabitEthernet0/0 overload
                        !
                        ipv6 access-list ACL_IPV6
                         permit tcp any host 2001:db8::1 eq 80
                         permit udp any host 2001:db8::1 eq 53
                        !
                        ipv6 route ::/0 2001:db8::2
                        !
                        ipv6 dhcp pool IPV6_POOL
                         dns-server 2001:db8::10
                         domain-name example.com
                        !
                        ip multicast-routing
                        !
                        ip pim rp-address 192.168.1.100
                        !
                        ip sla 1
                         icmp-echo 8.8.8.8
                         timeout 1000
                         frequency 60
                        !
                        track 1 ip sla 1 reachability
                        !
                        interface GigabitEthernet0/2
                         description Link to ISP
                         ip address 203.0.113.2 255.255.255.252
                        !
                        ip route 0.0.0.0 0.0.0.0 203.0.113.1
                        !
                        interface Serial0/0/0
                         description Link to Branch Office
                         ip address 172.16.1.1 255.255.255.252
                         clock rate 64000
                        !
                        router eigrp 100
                         network 172.16.1.0 0.0.0.3
                         network 10.0.0.0
                        !
                        aaa new-model
                        aaa authentication login default local
                        aaa authorization exec default local
                        !
                        line con 0
                         exec-timeout 0 0
                         privilege level 15
                         logging synchronous
                        line aux 0
                        line vty 0 4
                         login
                        !
                        end
                                                
                        Convert the above configuration output into JASON format.                        
                        """;

        return makeApiCallToDjangoServer(output);
    }

    private String makeApiCallToDjangoServer(String output) {
        String djangoServerEndPoint = "http://localhost:5000/";
        String fullUrl = djangoServerEndPoint + "?param=" + output;
        try {
            String response = restTemplate.getForObject(fullUrl, String.class);
            System.out.println("=====================================================\n" + response + "\n=============================================================");
            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "FAILED";

    }
}
