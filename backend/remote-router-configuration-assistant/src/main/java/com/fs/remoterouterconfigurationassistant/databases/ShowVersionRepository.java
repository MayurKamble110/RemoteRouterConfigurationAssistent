package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ShowVersionRepository extends JpaRepository<NetworkDeviceDao,String> {
}
