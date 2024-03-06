package com.fs.remoterouterconfigurationassistant.api;

import com.fs.remoterouterconfigurationassistant.RouterAccessDetails;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class routerApi {

    @Autowired
    routerApiService routerApiService;
    @PostMapping(path = "/connect")
    public StringBuilder makeSSHConnectionToRouter(@RequestBody RouterAccessDetails accessDetails)
    {
        StringBuilder responce = routerApiService.connectToRouter(accessDetails);


        return  responce;
    }
}
