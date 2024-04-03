package com.fs.remoterouterconfigurationassistant.databases.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "network_devices")
@AllArgsConstructor
@NoArgsConstructor
public class NetworkDeviceDao {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enable_password")
    private String enablePassword;

    @Column(name = "os_type")
    private String osType;

    @Column(name = "os_version")
    private String osVersion;

    @Column(name = "hardware_model")
    private String hardwareModel;

    @Column(name = "raw_logs")
    private String rawLogs;

    @Column(name = "device_type")
    private String deviceType;

}

