package com.fs.remoterouterconfigurationassistant.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandRequest {
    private  String command;

    @JsonProperty("device_id")
    private  Long  deviceId;

    public  String getCommand()
    {
        return  this.command;
    }

    public Long getDeviceId(){return  this.deviceId;};
}
