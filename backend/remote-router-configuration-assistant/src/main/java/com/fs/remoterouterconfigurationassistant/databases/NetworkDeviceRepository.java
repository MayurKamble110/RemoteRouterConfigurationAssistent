package com.fs.remoterouterconfigurationassistant.databases;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface NetworkDeviceRepository extends JpaRepository<NetworkDeviceDao,Long> {

    @Query("SELECT d FROM NetworkDeviceDao d WHERE d.user.emailID = :email")
    List<NetworkDeviceDao> findAllRoutersByUserAccount(String email);
}
