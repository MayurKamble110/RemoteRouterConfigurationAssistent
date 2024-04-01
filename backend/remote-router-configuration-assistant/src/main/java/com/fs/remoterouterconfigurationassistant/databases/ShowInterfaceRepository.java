package com.fs.remoterouterconfigurationassistant.databases;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fs.remoterouterconfigurationassistant.databases.entities.ShowInterfacesDao;

public interface ShowInterfaceRepository extends JpaRepository<ShowInterfacesDao,String> {

}
