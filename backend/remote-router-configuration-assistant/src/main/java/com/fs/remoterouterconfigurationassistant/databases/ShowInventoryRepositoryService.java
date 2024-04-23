package com.fs.remoterouterconfigurationassistant.databases;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
                    throws BadRequestException {
        ShowInventoryDto showInventoryDto=
                FlaskServer.getShowInventoryDto(new FlaskServerApiRequestBody(response,
                        "Give a JSON  object including hardwareName, hardwareDescription, productId, versionId and serialNumber. all these fields should be of string type."));
        if(showInventoryDto==null)
            throw new ResourceAccessException("Server is not responding...");
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
