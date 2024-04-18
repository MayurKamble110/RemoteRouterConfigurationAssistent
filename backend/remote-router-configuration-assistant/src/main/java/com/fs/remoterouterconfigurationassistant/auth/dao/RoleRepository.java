package com.fs.remoterouterconfigurationassistant.auth.dao;

import com.fs.remoterouterconfigurationassistant.auth.entities.Role;
import com.fs.remoterouterconfigurationassistant.auth.entities.RoleID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, RoleID> {
    @Query("SELECT role.role FROM Role role WHERE role.user.emailID = :emailID")
    List<String> findAllRoleStringsByEmailID(String emailID);

    @Query("SELECT role FROM Role role WHERE role.user.emailID = :emailID")
    List<Role> findAllRolesByEmailID(String emailID);
}
