package com.fs.remoterouterconfigurationassistant.databases.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "device_interface")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInterfaceDao {

    @Id
    @Column(name = "interface_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private NetworkDeviceDao networkDeviceDao;

    @Column(name = "interface_name")
    private String interfaceName;

    @Column(name = "is_connected")
    private boolean isConnected;

    @Column(name = "description")
    private String description;

    @Column(name = "raw_logs",length = 5000)
    private String rawLogs;

}
