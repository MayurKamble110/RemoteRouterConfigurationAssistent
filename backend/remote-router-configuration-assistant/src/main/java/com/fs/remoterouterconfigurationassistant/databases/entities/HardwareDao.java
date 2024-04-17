package com.fs.remoterouterconfigurationassistant.databases.entities;

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
@Table(name = "hardware")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HardwareDao {
    @Id
    @Column(name = "hardware_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private NetworkDeviceDao networkDeviceDao;

    @Column(name = "hardware_name")
    private String hardwareName;

    @Column(name = "hardware_description")
    private String hardwareDescription;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "version_id")
    private String versionId;

    @Column(name = "serial_number")
    private String serialNumber;
}
