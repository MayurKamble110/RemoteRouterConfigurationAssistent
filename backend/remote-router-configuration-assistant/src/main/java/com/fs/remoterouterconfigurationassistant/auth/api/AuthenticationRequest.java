package com.fs.remoterouterconfigurationassistant.auth.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String emailID;
    private String password;
}
