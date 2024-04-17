package com.fs.remoterouterconfigurationassistant.auth.api;

import com.fs.remoterouterconfigurationassistant.auth.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private UserDto userDto;
}
