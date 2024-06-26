package com.fs.remoterouterconfigurationassistant.api.routerCommands;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.databases.DeviceInterfaceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.ShowAccessModeRepository;
import com.fs.remoterouterconfigurationassistant.databases.ShowAccessModeRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.ShowInventoryRepositoryService;
import com.fs.remoterouterconfigurationassistant.databases.ShowVersionRepositoryService;
import org.springframework.web.client.ResourceAccessException;

@Service
public class RouterCommandInterpreter {

    @Autowired
    DeviceInterfaceRepositoryService deviceInterfaceRepositoryService;

    @Autowired
    ShowVersionRepositoryService showVersionRepositoryService;

    @Autowired
    NetworkDeviceRepositoryService networkDeviceRepositoryService;

    @Autowired
    ShowInventoryRepositoryService showInventoryRepositoryService;

    @Autowired
    ShowAccessModeRepositoryService showAccessModeRepositoryService;

    public void processor(StringBuilder response, CommandRequest commandRequest)
                    throws JsonProcessingException, ResourceAccessException, BadRequestException {
        switch (commandRequest.getCommand()) {
            case "show interface":
                 deviceInterfaceRepositoryService.saveDeviceInterfaceDataToDatabase(response.toString(),
                                commandRequest.getDeviceId());
                 break;
            case "show version":
                  showVersionRepositoryService.addVersionDataToDatabase(response.toString(),
                                commandRequest.getDeviceId());
                  break;
            case "show processes cpu history":
                networkDeviceRepositoryService.addCpuProcessesHistoryToDatabase(response.toString(),commandRequest.getDeviceId());
                break;
            case "show inventory":
                 showInventoryRepositoryService.saveHardwareDataToDatabase(response.toString(), commandRequest.getDeviceId());
                 break;
            case "show running config":
                showAccessModeRepositoryService.addAccessModeToDatabase(response.toString(), commandRequest.getDeviceId());
                break;
            default:
                System.out.println("Invalid command......");
        }

    }
}
