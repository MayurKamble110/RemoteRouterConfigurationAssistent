package com.fs.remoterouterconfigurationassistant.databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ShowInterfaceRepository extends JpaRepository<ShowInterfacesDao, String> {

}
