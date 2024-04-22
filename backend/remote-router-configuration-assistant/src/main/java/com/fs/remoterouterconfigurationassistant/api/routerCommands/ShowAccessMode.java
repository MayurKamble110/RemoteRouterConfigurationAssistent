package com.fs.remoterouterconfigurationassistant.api.routerCommands;

public class ShowAccessMode {
    public static String[] parseData(String response) {
        String[] interfaceList = response.split("!");

        for (String data : interfaceList) {
            data = data + "output buffers swapped out";
        }

        return interfaceList;
    }
}
