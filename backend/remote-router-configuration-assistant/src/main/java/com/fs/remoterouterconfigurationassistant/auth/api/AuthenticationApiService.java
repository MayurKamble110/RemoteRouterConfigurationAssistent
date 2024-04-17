package com.fs.remoterouterconfigurationassistant.auth.api;

import com.fs.remoterouterconfigurationassistant.auth.dao.RoleRepository;
import com.fs.remoterouterconfigurationassistant.auth.dao.UserRepository;
import com.fs.remoterouterconfigurationassistant.auth.dto.UserDto;
import com.fs.remoterouterconfigurationassistant.auth.entities.Role;
import com.fs.remoterouterconfigurationassistant.auth.entities.User;
import com.fs.remoterouterconfigurationassistant.auth.util.BcryptPasswordGenerator;
import com.fs.remoterouterconfigurationassistant.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationApiService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BcryptPasswordGenerator bcryptPasswordGenerator;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (authenticationRequest.getEmailID(),
                                    authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username of password",e);
        }

        final UserDetails userDetails=userDetailsService.
                loadUserByUsername(authenticationRequest.getEmailID());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Optional<User> user = userRepository.findById(userDetails.getUsername());

        UserDto userDto=UserDto.builder()
                .emailID(userDetails.getUsername())
                .name(user.get().getName())
                .build();
        return new AuthenticationResponse(token,userDto);
    }
    public void createUser(User user){
        User newUser=User.builder()
                .emailID(user.getEmailID())
                .name(user.getName())
                .password(bcryptPasswordGenerator.hashPassword(user.getPassword()))
                .build();

        User savedUser=userRepository.save(newUser);
        Role role=Role.builder()
                .user(savedUser)
                .role("ROLE_ADMIN")
                .build();
        roleRepository.save(role);
    }
}
