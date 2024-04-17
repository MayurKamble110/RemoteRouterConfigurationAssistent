package com.fs.remoterouterconfigurationassistant.auth.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordGenerator {
    public String hashPassword(String password_plaintext) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);

        return(BCrypt.hashpw(password_plaintext, salt));
    }
}

