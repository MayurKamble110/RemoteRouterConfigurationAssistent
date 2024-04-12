package com.fs.remoterouterconfigurationassistant.databases.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interfaces")
@Builder
public class ShowInterfacesDao {

    @Id
    private String name;

    private String ip_address;

    private String status;

    private String description;

    private String hardware;

    @Column(length = 2000)
    private String raw_information;

}
