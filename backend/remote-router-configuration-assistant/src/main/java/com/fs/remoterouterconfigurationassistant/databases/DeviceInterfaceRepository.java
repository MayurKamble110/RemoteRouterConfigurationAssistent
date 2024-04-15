package com.fs.remoterouterconfigurationassistant.databases;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;

@Repository
public interface DeviceInterfaceRepository extends JpaRepository<DeviceInterfaceDao,Long> {
}
