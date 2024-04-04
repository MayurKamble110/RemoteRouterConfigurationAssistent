package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

@Service
public class NetworkDeviceRepositoryService {

    @Autowired
    NetworkDeviceRepository networkDeviceRepository;

    public  void addNetworkDeviceToDatabase(NetworkDeviceDao networkDeviceDao)
    {
            networkDeviceRepository.save(networkDeviceDao);
    }

}
