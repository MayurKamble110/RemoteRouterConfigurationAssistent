package com.fs.remoterouterconfigurationassistant.databases;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.model.RouterVersionResponseDto;
import com.fs.remoterouterconfigurationassistant.api.model.ShowAccessModeDto;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.ShowAccessMode;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.ShowInterfaces;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;
@Service
public class  ShowAccessModeRepositoryService {

    @Autowired
    private ShowAccessModeRepository showAccessModeRepository;

    @Autowired
    private DeviceInterfaceRepository deviceInterfaceRepository;

    public Map listOfInterfaceAndAccessMode(String response) {
        String[] interfaces = ShowAccessMode.parseData(response);
        Map<String, String> dataMap = new HashMap<>();
        for (String data : interfaces) {
            if (data.contains("trunk")) {
                ShowAccessModeDto responceDto =
                        FlaskServer.getAccessMode(new FlaskServerApiRequestBody(data,
                                "Give a JSON  object including interfaceName and accessMode. all these fields should be of string type."));
                System.out.println("Length : " + data.length());
                String name = responceDto.getInterfaceName();


                dataMap.put(name, "trunk");
            } else if (data.contains("access")) {
                ShowAccessModeDto responceDto =
                        FlaskServer.getAccessMode(new FlaskServerApiRequestBody(data,
                                "Give a JSON  object including interfaceName and accessMode. all these fields should be of string type."));
                System.out.println("Length : " + data.length());
                String name = responceDto.getInterfaceName();


                dataMap.put(name, "access");
            }

        }
        return dataMap;
    }



    public void addAccessModeToDatabase(String response, Long deviceId) {

        Map<String ,String> mp = listOfInterfaceAndAccessMode(response);
        System.out.println(mp);
            List<DeviceInterfaceDao> in = deviceInterfaceRepository.findAll();
            for ( DeviceInterfaceDao iface : in) {
                if(iface.getNetworkDeviceDao().getDeviceId() == deviceId )
                {
                   String k= iface.getInterfaceName();
                   String t= mp.get(k);
                   DeviceInterfaceDao dao = DeviceInterfaceDao.builder()
                           .networkDeviceDao(iface.getNetworkDeviceDao())
                                   .id(iface.getId())
                                           .interfaceName(iface.getInterfaceName())
                                                   .description(iface.getDescription())
                                                           .isConnected(iface.isConnected())
                                                                   .rawLogs(iface.getRawLogs())
                                                                           .mode(t).
                    build();

                    deviceInterfaceRepository.save(dao);
                }
            }
        }

    }



