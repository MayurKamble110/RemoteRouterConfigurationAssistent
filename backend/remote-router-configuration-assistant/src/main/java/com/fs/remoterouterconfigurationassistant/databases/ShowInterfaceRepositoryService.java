package com.fs.remoterouterconfigurationassistant.databases;

import java.util.ArrayList;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

public class ShowInterfaceRepositoryService{


    public void addInterfacesToDatabase(String[] interfaces)
    {
            for (String data : interfaces)
            {
                if(data.contains("GigabitEthernet"))
                {
                    RouterInterfaceResponceDto responce = FlaskServer.makeRequest(new FlaskServerApiRequestBody(data,"Give a JSON  object including name,status,ip_address,description and hardware."));
                    System.out.println("====================================="+responce+"\n======================================");
                }
            }
    }
}
