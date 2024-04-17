package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterVersionResponseDto;
import com.fs.remoterouterconfigurationassistant.api.model.ShowInventoryDto;
import com.fs.remoterouterconfigurationassistant.databases.entities.HardwareDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;


@Service
public class ShowInventoryRepositoryService {
    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    @Autowired
    ShowInventoryRepository showInventoryRepository;

    public void saveHardwareDataToDatabase(String response, Long deviceId)
    {
        ShowInventoryDto showInventoryDto=
                FlaskServer.getShowInventoryDto(new FlaskServerApiRequestBody(response,
                        "Give a JSON  object including hardwareName, hardwareDescription, productId, versionId and serialNumber. all these fields should be of string type."));
        NetworkDeviceDao deviceDao = networkDeviceRepository.getReferenceById(deviceId);
        HardwareDao hardwareDao = HardwareDao.builder()
                .hardwareName(showInventoryDto.getHardwareName())
                .networkDeviceDao(deviceDao)
                .productId(showInventoryDto.getProductId())
                .versionId(showInventoryDto.getVersionId())
                .serialNumber(showInventoryDto.getSerialNumber())
                .hardwareDescription(showInventoryDto.getHardwareDescription())
                .build();
        showInventoryRepository.save(hardwareDao);
    }
}
