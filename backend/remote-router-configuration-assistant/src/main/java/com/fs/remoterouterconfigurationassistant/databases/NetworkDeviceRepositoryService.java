package com.fs.remoterouterconfigurationassistant.databases;

import java.lang.module.ResolutionException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;
import org.springframework.web.client.ResourceAccessException;

/*
* new FlaskServerApiRequestBody(response,"Given is the CPU processes history of a router device. " +
                        "Parse this into JSON format consisting of 'x' and 'y' coordinates for each separated graph with their proper names. ")
* */
@Service
public class NetworkDeviceRepositoryService {

    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    public  void addNetworkDeviceToDatabase(NetworkDeviceDao networkDeviceDao)
    {
            networkDeviceRepository.save(networkDeviceDao);
    }

    public void addCpuProcessesHistoryToDatabase(String response,Long deviceid) throws BadRequestException, JsonParseException, ResourceAccessException {
        String parsedResponse = FlaskServer.getParsedCpuProcessHistoryData(new FlaskServerApiRequestBody(response,"Given is the CPU processes history of a router device. " +
                        "Parse this into JSON format consisting of 'x' and 'y' coordinates for each separated graph with their proper names. "));

        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceid);
        if(networkDeviceDao.isPresent())
        {
            networkDeviceDao.get().setRawCpuProcessesHistory(response);
            networkDeviceDao.get().setParsedCpuProcessesHistory(parsedResponse);

            networkDeviceRepository.save(networkDeviceDao.get());
        }else {
            throw new BadRequestException("Requested resource is not found.");
        }
    }

}
