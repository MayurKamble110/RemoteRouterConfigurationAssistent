package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.HardwareDao;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ShowInventoryRepository extends JpaRepository<HardwareDao,Long> {
}
