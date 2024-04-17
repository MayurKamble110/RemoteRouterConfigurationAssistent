package com.fs.remoterouterconfigurationassistant.auth.user_services;

import com.fs.remoterouterconfigurationassistant.auth.dao.RoleRepository;
import com.fs.remoterouterconfigurationassistant.auth.dao.UserRepository;
import com.fs.remoterouterconfigurationassistant.auth.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String emailID) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findById(emailID);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }
        User user = userOptional.get();
        List<String> roles = roleRepository.findByEmailID(emailID);
        return new CustomUserDetails(user,roles);
    }
}
