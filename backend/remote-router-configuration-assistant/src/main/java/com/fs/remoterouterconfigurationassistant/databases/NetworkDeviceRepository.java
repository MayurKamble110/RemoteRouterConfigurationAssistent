package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

@Repository
public interface NetworkDeviceRepository extends JpaRepository<NetworkDeviceDao,Long> {
}
