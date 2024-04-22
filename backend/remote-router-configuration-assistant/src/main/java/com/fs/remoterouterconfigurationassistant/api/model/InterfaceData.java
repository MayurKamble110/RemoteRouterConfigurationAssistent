package com.fs.remoterouterconfigurationassistant.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;

public class InterfaceData {

    @JsonProperty("up_interface_count")
    int upInterfaceCount;
    @JsonProperty("down_interface_count")
    int downInterfaceCount;

    List<DeviceInterfaceDao> interfaces;

    public int getUpInterfaceCount() {
        return upInterfaceCount;
    }

    public void setUpInterfaceCount(int upInterfaceCount) {
        this.upInterfaceCount = upInterfaceCount;
    }

    public int getDownInterfaceCount() {
        return downInterfaceCount;
    }

    public void setDownInterfaceCount(int downInterfaceCount) {
        this.downInterfaceCount = downInterfaceCount;
    }

    public List<DeviceInterfaceDao> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<DeviceInterfaceDao> interfaces) {
        this.interfaces = interfaces;
    }
}

