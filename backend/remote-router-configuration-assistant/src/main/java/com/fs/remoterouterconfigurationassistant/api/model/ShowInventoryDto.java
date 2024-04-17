package com.fs.remoterouterconfigurationassistant.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShowInventoryDto {
    String hardwareName;
    String hardwareDescription;
    String productId;
    String versionId;
    String serialNumber;

}
