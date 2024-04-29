package com.fs.remoterouterconfigurationassistant.databases.entities;

import com.fs.remoterouterconfigurationassistant.auth.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "network_devices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkDeviceDao {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

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

    @Column(name = "os_type",columnDefinition = "VARCHAR(255) DEFAULT 'N/A'")
    private String osType;

    @Column(name = "os_version",columnDefinition = "VARCHAR(255) DEFAULT 'N/A'")
    private String osVersion;

    @Column(name = "hardware_model",columnDefinition = "VARCHAR(255) DEFAULT 'N/A'")
    private String hardwareModel;

    @Column(name = "raw_logs",columnDefinition = "VARCHAR(5000) DEFAULT 'N/A'")
    private String rawLogs;

    @Column(name = "device_type",columnDefinition = "VARCHAR(255) DEFAULT 'N/A'")
    private String deviceType;

    @Column(name = "raw_cpu_processes_history",columnDefinition = "VARCHAR(5000) DEFAULT 'N/A'")
    private String rawCpuProcessesHistory;

    @Column(name = "parsed_cpu_processes_history",columnDefinition = "VARCHAR(5000) DEFAULT 'N/A'")
    private String parsedCpuProcessesHistory;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private User user;

    @Override
    public String toString() {
        return "Device {\n" +
                        "deviceId=" + deviceId +
                        ", \ndeviceName='" + deviceName + '\'' +
                        ", \nipAddress='" + ipAddress + '\'' +
                        ", \nusername='" + username + '\'' +
                        ", \npassword='" + password + '\'' +
                        ", \nenablePassword='" + enablePassword + '\'' +
                        ", \nosType='" + osType + '\'' +
                        ", \nosVersion='" + osVersion + '\'' +
                        ", \nhardwareModel='" + hardwareModel + '\'' +
                        ", \nrawLogs='" + rawLogs + '\'' +
                        ", \ndeviceType='" + deviceType + '\'' +
                        '}';
    }

}

