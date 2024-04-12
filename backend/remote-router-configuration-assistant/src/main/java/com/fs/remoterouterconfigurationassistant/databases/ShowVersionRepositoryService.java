package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterVersionResponseDto;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;

@Service
public class ShowVersionRepositoryService {
    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;


    public void addVersionDataToDatabase(String response, Long deviceId)
    {
        RouterVersionResponseDto routerVersionResponseDto=
        FlaskServer.getRouterVersionResponseDto(new FlaskServerApiRequestBody(response,
                "Give a JSON  object including os_type and os_version. all these fields should be of string type."));
        NetworkDeviceDao deviceDao = networkDeviceRepository.getReferenceById(deviceId);
        NetworkDeviceDao deviceDao1 = NetworkDeviceDao.builder()
                .deviceId(deviceDao.getDeviceId())
                .deviceName(deviceDao.getDeviceName())
                .ipAddress(deviceDao.getIpAddress())
                .username(deviceDao.getUsername())
                .password(deviceDao.getPassword())
                .enablePassword(deviceDao.getEnablePassword())
                .osType(routerVersionResponseDto.getOs_type())
                .osVersion(routerVersionResponseDto.getOs_version())
                .hardwareModel(deviceDao.getHardwareModel())
                .rawLogs(deviceDao.getRawLogs())
                .deviceType(deviceDao.getDeviceType())
                .build();
        networkDeviceRepository.save(deviceDao1);
    }
}
