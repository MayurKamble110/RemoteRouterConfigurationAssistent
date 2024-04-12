package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.ShowInterfaces;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

@Service
public class DeviceInterfaceRepositoryService {

    @Autowired
    DeviceInterfaceRepository deviceInterfaceRepository;

    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    public void saveDeviceInterfaceDataToDatabase(String responce, Long deviceid) {

        String[] interfaces = ShowInterfaces.parseData(responce);

        for (String data : interfaces) {
            if (data.contains("GigabitEthernet")) {
                RouterInterfaceResponceDto responceDto =
                                FlaskServer.getRouterInterfaceResponceDto(new FlaskServerApiRequestBody(
                                                data,
                                                "Only give name,status,ip_address,description and hardware in JSON format it should be a single JSON object containing only given four fields. all these fields should be of string type."));
                System.out.println("Inserting a row in database.....");
                NetworkDeviceDao networkDeviceDao =
                                networkDeviceRepository.getReferenceById(deviceid);
                assert responceDto != null;
                boolean status = responceDto.getStatus().equals("up");
                DeviceInterfaceDao dao = DeviceInterfaceDao.builder()
                                .interfaceName(responceDto.getName())
                                .networkDeviceDao(networkDeviceDao)
                                .isConnected(status)
                                .description(responceDto.getDescription())
                                .rawLogs(data)
                                .build();
                deviceInterfaceRepository.save(dao);
            }
        }
    }
}
