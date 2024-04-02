package com.fs.remoterouterconfigurationassistant.databases;

import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

@Service
public class ShowInterfaceRepositoryService {

    @Autowired
    private ShowInterfaceRepository repository;

    public void addInterfacesToDatabase(String[] interfaces) {
        for (String data : interfaces) {
            if (data.contains("GigabitEthernet")) {
                data=data+" output buffers swapped out";
                RouterInterfaceResponceDto responce =
                                FlaskServer.makeRequest(new FlaskServerApiRequestBody(data,
                                                "Give a JSON  object including name,status,ip_address,description and hardware. all these fields should be of string type."));
                System.out.println("Length : " + data.length());
                ShowInterfacesDao dao = ShowInterfacesDao.builder()
                                .status(responce.getStatus())
                                .name(responce.getName())
                                .description(responce.getDescription())
                                .hardware(responce.getHardware())
                                .ip_address(responce.getIp_address())
                                .raw_information(data)
                                .build();
                System.out.println("Inserting a row in database.....");
                repository.save(dao);

            }
        }
    }
}
