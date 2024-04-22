package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
@Repository
public interface ShowAccessModeRepository extends JpaRepository<DeviceInterfaceDao,Long> {
}
