package com.fs.remoterouterconfigurationassistant.auth.api;

import com.fs.remoterouterconfigurationassistant.auth.dao.RoleRepository;
import com.fs.remoterouterconfigurationassistant.auth.dao.UserRepository;
import com.fs.remoterouterconfigurationassistant.auth.entities.User;
import com.fs.remoterouterconfigurationassistant.auth.util.BcryptPasswordGenerator;
import com.fs.remoterouterconfigurationassistant.auth.util.JwtUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationApi {

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

    @Autowired
    private AuthenticationApiService delegate;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception{
        return ResponseEntity.ok(delegate.authenticate(authenticationRequest));
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            delegate.createUser(user);
            return new ResponseEntity<>("OK",HttpStatus.CREATED);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-user/{emailID}")
    public ResponseEntity<?> deleteUser(@PathVariable String emailID){
        try{
            delegate.deleteUser(emailID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
