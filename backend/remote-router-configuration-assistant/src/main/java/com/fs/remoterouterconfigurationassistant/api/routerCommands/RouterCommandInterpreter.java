package com.fs.remoterouterconfigurationassistant.api.routerCommands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.databases.ShowInterfaceRepositoryService;

@Service
public class RouterCommandInterpreter {

    @Autowired
    ShowInterfaceRepositoryService showInterfaceRepositoryService;

    public void processor(StringBuilder response,String command)
    {
        switch (command)
        {
            case "show interface" :
               showInterfaceRepositoryService.addInterfacesToDatabase(response.toString());
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
