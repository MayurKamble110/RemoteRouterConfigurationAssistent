package com.fs.remoterouterconfigurationassistant.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CpuProcessHistoryDto {
    @JsonProperty("x")
    private List<String> xValues;

    @JsonProperty("y")
    private List<String> yValues;

    // Getters and setters

    public List<String> getxValues() {
        return xValues;
    }

    public void setxValues(List<String> xValues) {
        this.xValues = xValues;
    }

    public List<String> getyValues() {
        return yValues;
    }

    public void setyValues(List<String> yValues) {
        this.yValues = yValues;
    }

    @Override
    public String toString() {
        return "CPUData{" +
                        "xValues=" + xValues +
                        ", yValues=" + yValues +
                        '}';
    }

}
