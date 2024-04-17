package com.fs.remoterouterconfigurationassistant.auth.dao;

import com.fs.remoterouterconfigurationassistant.auth.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String> {
}
