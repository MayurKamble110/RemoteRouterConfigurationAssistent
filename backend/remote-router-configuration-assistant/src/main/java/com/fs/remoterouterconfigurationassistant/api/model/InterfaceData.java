package com.fs.remoterouterconfigurationassistant.api.model;

public class InterfaceData {
    private String name;
    private String ipAddress;
    private String subnetMask;
    private String mtu;
    private String reliability;
    private String txLoad;
    private String rxLoad;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getMtu() {
        return mtu;
    }

    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getTxLoad() {
        return txLoad;
    }

    public void setTxLoad(String txLoad) {
        this.txLoad = txLoad;
    }

    public String getRxLoad() {
        return rxLoad;
    }

    public void setRxLoad(String rxLoad) {
        this.rxLoad = rxLoad;
    }

    @Override
    public String toString() {
        return "InterfaceData{" +
                "name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", subnetMask='" + subnetMask + '\'' +
                ", mtu='" + mtu + '\'' +
                ", reliability='" + reliability + '\'' +
                ", txLoad='" + txLoad + '\'' +
                ", rxLoad='" + rxLoad + '\'' +
                '}';
    }
}

