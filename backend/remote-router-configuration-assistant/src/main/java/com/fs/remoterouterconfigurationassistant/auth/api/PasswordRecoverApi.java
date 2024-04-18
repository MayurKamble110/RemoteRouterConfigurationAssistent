package com.fs.remoterouterconfigurationassistant.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class PasswordRecoverApi {
    @Autowired
    private PasswordRecoveryApiService delegate;

    @GetMapping("/is-account-exist/{emailID}")
    public Boolean isAccountExist(@PathVariable String emailID){
        return delegate.isAccountExists(emailID);
    }

    @PostMapping("/send-otp/{emailID}")
    public ResponseEntity<?> sendOtp(@PathVariable String emailID){
        if( ! isAccountExist(emailID))
            return new ResponseEntity<>("No Account with this email id exist.",HttpStatus.BAD_REQUEST);
        try {
            delegate.sendOtp(emailID);
            return ResponseEntity.ok("OTP sent successfully");
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
