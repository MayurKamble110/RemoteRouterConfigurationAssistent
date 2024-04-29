package com.fs.remoterouterconfigurationassistant.api.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDevice {
    private String deviceName;

    private String ipAddress;

    private String username;

    private String password;

    private String enablePassword;
}
