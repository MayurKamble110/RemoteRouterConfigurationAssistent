package com.fs.remoterouterconfigurationassistant.databases;
import java.util.List;
import java.util.Optional;

import com.fs.remoterouterconfigurationassistant.databases.NetworkDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void saveDeviceInterfaceDataToDatabase(String responce, Long deviceid) {
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
                assert responceDto != null;
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
                            deviceInterfaceRepository.findByInterfaceName(responceDto.getName());
                    dao.setConnected(status);
                    dao.setDescription(responceDto.getDescription());
                    dao.setRawLogs(data);
                    deviceInterfaceRepository.save(dao);
                    System.out.println("Updating Interface : " + dao.getInterfaceName());
                }
            }
        }
    }
}
 