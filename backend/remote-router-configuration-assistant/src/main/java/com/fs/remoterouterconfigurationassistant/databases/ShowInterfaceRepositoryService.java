package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.CommandRequest;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.ShowInterfaces;
import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

@Service
public class ShowInterfaceRepositoryService {

    @Autowired
    private ShowInterfaceRepository repository;



    public void addInterfacesToDatabase(String responce, CommandRequest commandRequest) {

        String[] interfaces = ShowInterfaces.parseData(responce);

        for (String data : interfaces) {
            if (data.contains("GigabitEthernet")) {
                RouterInterfaceResponceDto responceDto =
                                FlaskServer.makeRequest(new FlaskServerApiRequestBody(data,
                                                "Give a JSON  object including name,status,ip_address,description and hardware. all these fields should be of string type."));
                System.out.println("Length : " + data.length());
                ShowInterfacesDao dao = ShowInterfacesDao.builder()
                                .status(responceDto.getStatus())
                                .name(responceDto.getName())
                                .description(responceDto.getDescription())
                                .hardware(responceDto.getHardware())
                                .ip_address(responceDto.getIp_address())
                                .raw_information(data)
                                .build();
                System.out.println("Inserting a row in database.....");


            }
        }
    }
}
