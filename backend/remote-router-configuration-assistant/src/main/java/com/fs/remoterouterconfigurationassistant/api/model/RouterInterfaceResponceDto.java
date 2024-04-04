package com.fs.remoterouterconfigurationassistant.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RouterInterfaceResponceDto {
    String name;
    String status;
    String ip_address;
    String description;
    String hardware;

    @Override
    public String toString() {
        return "RouterInterfaceResponseDto\n{" +
                        "\nname='" + name + '\'' +
                        ", \nstatus='" + status + '\'' +
                        ", \nip_address='" + ip_address + '\'' +
                        ", \ndescription='" + description + '\'' +
                        ", \nhardware='" + hardware + '\'' +
                        '}';
    }

}
