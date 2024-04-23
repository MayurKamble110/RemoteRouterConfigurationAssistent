package com.fs.remoterouterconfigurationassistant.databases;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.routerCommands.ShowInterfaces;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

@Service
public class DeviceInterfaceRepositoryService {
    @Autowired
    DeviceInterfaceRepository deviceInterfaceRepository;
    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    public void saveDeviceInterfaceDataToDatabase(String responce, Long deviceid)
                    throws  BadRequestException {
        String[] interfaces = ShowInterfaces.parseData(responce);
        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceid);
        List<DeviceInterfaceDao> list =
                        deviceInterfaceRepository.findByNetworkDeviceDao(networkDeviceDao.get());

        for (String data : interfaces) {
            if (data.contains("GigabitEthernet")) {
                RouterInterfaceResponceDto responceDto =
                                FlaskServer.getRouterInterfaceResponceDto(new FlaskServerApiRequestBody(
                                                data,
                                                "Only give name,status,ip_address,description and hardware in JSON format it should be a single JSON object containing only given four fields. all these fields should be of string type."));

                if(responceDto==null)
                    throw new BadRequestException("Server is not responding...");

                boolean status = responceDto.getStatus().equals("up");

                if (list.isEmpty()) {
                    DeviceInterfaceDao dao = DeviceInterfaceDao.builder()
                                    .interfaceName(responceDto.getName())
                                    .networkDeviceDao(networkDeviceDao.get())
                                    .isConnected(status)
                                    .description(responceDto.getDescription())
                                    .rawLogs(data)
                                    .build();
                    deviceInterfaceRepository.save(dao);
                    System.out.println("Inserting a row in database.....");
                } else {
                    DeviceInterfaceDao dao =
                                    deviceInterfaceRepository.findByInterfaceNameAndDeviceId(
                                                    responceDto.getName(),
                                                    deviceid);
                    dao.setConnected(status);
                    dao.setDescription(responceDto.getDescription());
                    dao.setRawLogs(data);
                    deviceInterfaceRepository.save(dao);
                    System.out.println("Updating Interface : " + dao.getInterfaceName());
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println("All interfaces for device " + networkDeviceDao.get()
                            .getDeviceName() + " are ADDED successfully.");
        } else {
            System.out.println("All interfaces for device " + networkDeviceDao.get()
                            .getDeviceName() + " are UPDATED successfully.");
        }


    }
}
