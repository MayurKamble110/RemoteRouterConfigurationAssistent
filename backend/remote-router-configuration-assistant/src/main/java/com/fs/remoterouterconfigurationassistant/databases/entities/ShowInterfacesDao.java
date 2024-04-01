package com.fs.remoterouterconfigurationassistant.databases.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "interfaces")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowInterfacesDao {

    @Id
    private String ip_address;

    private String name;

    private String status;

    private String description;

    private String hardware;

    private String raw_information;

}
