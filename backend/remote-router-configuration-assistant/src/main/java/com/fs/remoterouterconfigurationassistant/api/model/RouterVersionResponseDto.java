package com.fs.remoterouterconfigurationassistant.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RouterVersionResponseDto {
    String os_type;
    String os_version;

}
