package com.fs.remoterouterconfigurationassistant.api.routerCommands;


import java.util.ArrayList;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

public class ShowInterfaces {

    public static String[] parseData(String responce) {
        String[] interfaceList = responce.split("output buffers swapped out");

        for (String data : interfaceList) {
            data = data + "output buffers swapped out";
        }

        return interfaceList;
    }

}
