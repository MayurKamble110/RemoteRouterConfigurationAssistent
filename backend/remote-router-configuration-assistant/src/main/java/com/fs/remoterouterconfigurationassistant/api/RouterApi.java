package com.fs.remoterouterconfigurationassistant.api;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
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


    @PostMapping(path = "/connect")
    public String makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails) {
        boolean response = routerApiService.connectToRouter(accessDetails);

        return response ? "1" : "0";
    }

    @PostMapping(path = "/commands")
    public String executeCommandOnRouter() {

        String response = routerApiService.executeCommandOnRouter();

        System.out.println(response);


        return response;//makeApiCallToFlaskServer(new ApiRequestBody(response,"Separate the data according to the component and give it in JASON  format and also give data of each component in raw text format."));
    }


    @PostMapping(path = "/disconnect")
    public String disconnectFromRouter() {

        return routerApiService.disconnectSSH();
    }

    @PostMapping(path = "/flask")
    public String makeApiCall() {
        String output = """
                        Vlan1 is administratively down, line protocol is down
                          Hardware is EtherSVI, address is 00b1.e383.a1c0 (bia 00b1.e383.a1c0)
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not supported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input 00:00:01, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             5991715 packets input, 2039409950 bytes, 0 no buffer
                             Received 0 broadcasts (0 IP multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 1 interface resets
                             0 unknown protocol drops
                             0 output buffer failures, 0 output buffers swapped out
                        Vlan128 is up, line protocol is up
                          Hardware is EtherSVI, address is 00b1.e383.a1c1 (bia 00b1.e383.a1c1)
                          Internet address is 10.16.128.34/24
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not supported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input 00:00:00, output 00:00:00, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 12000 bits/sec, 14 packets/sec
                          5 minute output rate 20000 bits/sec, 8 packets/sec
                             657331329 packets input, 52146164403 bytes, 0 no buffer
                             Received 0 broadcasts (0 IP multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             269398920 packets output, 51903034018 bytes, 0 underruns
                             0 output errors, 2 interface resets
                             0 unknown protocol drops
                             0 output buffer failures, 0 output buffers swapped out
                        Vlan1687 is up, line protocol is up
                          Hardware is EtherSVI, address is 00b1.e383.a1c3 (bia 00b1.e383.a1c3)
                          Internet address is 10.16.177.123/26
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not supported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input 00:00:01, output 00:00:00, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             79853151 packets input, 5091972097 bytes, 0 no buffer
                             Received 0 broadcasts (0 IP multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             283686030 packets output, 43588905692 bytes, 0 underruns
                             0 output errors, 1 interface resets
                             0 unknown protocol drops
                             0 output buffer failures, 0 output buffers swapped out
                        Vlan1688 is up, line protocol is up
                          Hardware is EtherSVI, address is 00b1.e383.a1c2 (bia 00b1.e383.a1c2)
                          Internet address is 10.16.178.125/26
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not supported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input 00:00:39, output 00:00:39, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             3714880 packets input, 318279354 bytes, 0 no buffer
                             Received 0 broadcasts (0 IP multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             990514 packets output, 63936115 bytes, 0 underruns
                             0 output errors, 1 interface resets
                             0 unknown protocol drops
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/1 is up, line protocol is up (connected)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a181 (bia 00b1.e383.a181)
                          Description: AutomationTestingPort
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Full-duplex, 1000Mb/s, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output 00:00:01, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 1000 bits/sec, 1 packets/sec
                             220092869 packets input, 92466190382 bytes, 0 no buffer
                             Received 1214108 broadcasts (960235 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 960235 multicast, 1482 pause input
                             0 input packets with dribble condition detected
                             309664947 packets output, 75578294825 bytes, 0 underruns
                             0 output errors, 0 collisions, 3891 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/2 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a182 (bia 00b1.e383.a182)
                          Description: AutomationTestingPort2
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output 8w5d, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             1665353 packets input, 514740395 bytes, 0 no buffer
                             Received 116857 broadcasts (98381 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 98381 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             1809685 packets output, 332641650 bytes, 0 underruns
                             0 output errors, 0 collisions, 451 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/3 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a183 (bia 00b1.e383.a183)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/4 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a184 (bia 00b1.e383.a184)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/5 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a185 (bia 00b1.e383.a185)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 3 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/6 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a186 (bia 00b1.e383.a186)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 2 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/7 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a187 (bia 00b1.e383.a187)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/8 is up, line protocol is up (connected)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a188 (bia 00b1.e383.a188)
                          Description: Mgmt
                          MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive set (10 sec)
                          Full-duplex, 1000Mb/s, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input 00:00:00, output 00:00:00, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 827000 bits/sec, 1121 packets/sec
                          5 minute output rate 33000 bits/sec, 13 packets/sec
                             54225191533 packets input, 5965698045217 bytes, 0 no buffer
                             Received 8454745302 broadcasts (3016272001 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 3016272001 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             807728857 packets output, 193405273761 bytes, 0 underruns
                             0 output errors, 0 collisions, 2 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/9 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a189 (bia 00b1.e383.a189)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not set
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/10 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a18a (bia 00b1.e383.a18a)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not set
                          Auto-duplex, Auto-speed, media type is 10/100/1000BaseTX
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/11 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a18b (bia 00b1.e383.a18b)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not set
                          Auto-duplex, Auto-speed, link type is auto, media type is Not Present
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                        GigabitEthernet0/12 is down, line protocol is down (notconnect)
                          Hardware is Gigabit Ethernet, address is 00b1.e383.a18c (bia 00b1.e383.a18c)
                          MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,
                             reliability 255/255, txload 1/255, rxload 1/255
                          Encapsulation ARPA, loopback not set
                          Keepalive not set
                          Auto-duplex, Auto-speed, link type is auto, media type is Not Present
                          input flow-control is off, output flow-control is unsupported
                          ARP type: ARPA, ARP Timeout 04:00:00
                          Last input never, output never, output hang never
                          Last clearing of "show interface" counters never
                          Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                          Queueing strategy: fifo
                          Output queue: 0/40 (size/max)
                          5 minute input rate 0 bits/sec, 0 packets/sec
                          5 minute output rate 0 bits/sec, 0 packets/sec
                             0 packets input, 0 bytes, 0 no buffer
                             Received 0 broadcasts (0 multicasts)
                             0 runts, 0 giants, 0 throttles
                             0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                             0 watchdog, 0 multicast, 0 pause input
                             0 input packets with dribble condition detected
                             0 packets output, 0 bytes, 0 underruns
                             0 output errors, 0 collisions, 1 interface resets
                             0 unknown protocol drops
                             0 babbles, 0 late collision, 0 deferred
                             0 lost carrier, 0 no carrier, 0 pause output
                             0 output buffer failures, 0 output buffers swapped out
                                                
                     
                        """;

        String[] list = output.split("output buffers swapped out");

        test(list);
        return "Hi";
        //return makeApiCallToFlaskServer(new FlaskServerApiRequestBody(output,"Seprate the interfaces informatio give the json data for all interfaces only take the gigabit interfaces."));
    }

    private void test(String[] list)
    {
        ShowInterfaceRepositoryService service = new ShowInterfaceRepositoryService();

        service.addInterfacesToDatabase(list);
    }
    private String makeApiCallToFlaskServer(FlaskServerApiRequestBody body) {
        String flaskApiUrl = "http://localhost:5000/getjson";

        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate =  new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        try {
            String jsonBody = objectMapper.writeValueAsString(body);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

            String response = restTemplate.postForObject(flaskApiUrl, requestEntity, String.class);
            System.out.println("=====================================================\n" + response + "\n=============================================================");
            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "FAILED";

    }

    @PostMapping("/test")
    public  void  testApi()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("""
                        GigabitEthernet0/1 is up, line protocol is up (connected)\\n" +
                                                "Hardware is Gigabit Ethernet, address is 00b1.e383.a181 (bia 00b1.e383.a181)\\n" +
                                                "Description: AutomationTestingPort\\n" +
                                                "MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,\\n" +
                                                "reliability 255/255, txload 1/255, rxload 1/255\\n" +
                                                "Encapsulation ARPA, loopback not set\\n" +
                                                "Keepalive set (10 sec)\\n" +
                                                "Full-duplex, 1000Mb/s, media type is 10/100/1000BaseTX\\n" +
                                                "input flow-control is off, output flow-control is unsupported\\n" +
                                                "ARP type: ARPA, ARP Timeout 04:00:00\\n" +
                                                "Last input never, output 00:00:01, output hang never\\n" +
                                                "Last clearing of \\"show interface\\" counters never\\n" +
                                                "Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0\\n" +
                                                "Queueing strategy: fifo\\n" +
                                                "Output queue: 0/40 (size/max)\\n" +
                                                "5 minute input rate 0 bits/sec, 0 packets/sec\\n" +
                                                "5 minute output rate 1000 bits/sec, 1 packets/sec\\n" +
                                                "218350925 packets input, 91682128302 bytes, 0 no buffer\\n" +
                                                "Received 1180257 broadcasts (934878 multicasts)\\n" +
                                                "0 runts, 0 giants, 0 throttles\\n" +
                                                "0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored\\n" +
                                                "0 watchdog, 934878 multicast, 1482 pause input\\n" +
                                                "0 input packets with dribble condition detected\\n" +
                                                "305703450 packets output, 74031920512 bytes, 0 underruns\\n" +
                                                "0 output errors, 0 collisions, 3871 interface resets\\n" +
                                                "0 unknown protocol drops\\n" +
                                                "0 babbles, 0 late collision, 0 deferred\\n" +
                                                "0 lost carrier, 0 no carrier, 0 pause output\\n" +
                                                "0 output buffer failures, 0 output buffers swapped out\\n"
                        """);
    ShowInterfaceRepositoryService service = new ShowInterfaceRepositoryService();
        //service.addInterfacesToDatabase(list);
    }
}
