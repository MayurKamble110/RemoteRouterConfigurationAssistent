package com.fs.remoterouterconfigurationassistant.api.routerCommands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.databases.DeviceInterfaceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;

@Service
public class RouterCommandInterpreter {

//    @Autowired
//    ShowInterfaceRepositoryService showInterfaceRepositoryService;

    @Autowired
    DeviceInterfaceRepositoryService deviceInterfaceRepositoryService;

    public void processor(StringBuilder response, CommandRequest commandRequest)
    {
        switch (commandRequest.getCommand())
        {
            case "show interface" :
               deviceInterfaceRepositoryService.saveDeviceInterfaceDateToDatabase(response.toString(),commandRequest.getDeviceId());
               break;
            case "show version":
                System.out.println("Yet to write logic");
                break;
            case "show ip route" :
                System.out.println("Yet to write logic");
                break;
            default:
                System.out.println("Invalid command......");
        }
    }

}
