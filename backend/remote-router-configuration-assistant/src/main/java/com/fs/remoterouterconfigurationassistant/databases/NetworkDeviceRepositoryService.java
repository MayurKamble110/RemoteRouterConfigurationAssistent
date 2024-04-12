package com.fs.remoterouterconfigurationassistant.databases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
import com.fs.remoterouterconfigurationassistant.flaskserver.FlaskServer;
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

    public void addCpuProcessesHistoryToDatabase(String response,Long deviceid)
    {
        String parsedResponse = FlaskServer.getParsedCpuProcessHistoryData(new FlaskServerApiRequestBody(response,"Given is the CPU processes history of a router device. " +
                        "Parse this into JSON format consisting of 'x' and 'y' coordinates for each separated graph with their proper names. "));

        Optional<NetworkDeviceDao> networkDeviceDao = networkDeviceRepository.findById(deviceid);
        if(networkDeviceDao.isPresent())
        {
            networkDeviceDao.get().setRawCpuProcessesHistory(response);
            networkDeviceDao.get().setParsedCpuProcessesHistory(parsedResponse);

            networkDeviceRepository.save(networkDeviceDao.get());
        }

    }

}
