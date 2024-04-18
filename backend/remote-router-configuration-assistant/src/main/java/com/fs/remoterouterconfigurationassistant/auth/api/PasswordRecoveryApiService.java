package com.fs.remoterouterconfigurationassistant.auth.api;

import com.fs.remoterouterconfigurationassistant.auth.dao.UserRepository;
import com.fs.remoterouterconfigurationassistant.auth.util.EmailSender;
import com.fs.remoterouterconfigurationassistant.auth.util.OTPGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasswordRecoveryApiService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private OTPGenerator otpGenerator;


    public Boolean isAccountExists(String emailID){
        return userRepository.existsById(emailID);
    }

    public void sendOtp(String emailID){
        String otp = otpGenerator.generateOTP(6);
        emailSender.sendEmail(emailID,"OTP to recover email ID","OTP is : "+ otp);
    }
}
