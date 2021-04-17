package com.dogecoin.cryptoapi.controllers;

import com.dogecoin.cryptoapi.services.StatusService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class StatusController {

    @Autowired
    StatusService statusService;

    Logger logger = LoggerFactory.getLogger(StatusController.class);


    @PostMapping("/create/status")
    public String createStatus(@RequestBody String data) {
        logger.info("Inside createStatus()");
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        String statusName = jsonObject.get("name").getAsString();
        Integer statusId = statusService.addStatus(statusName, 1);
        HashMap<String,Object> map = new HashMap<>();
        map.put("statusId", statusId);
        map.put("status", "success");
        return  new Gson().toJson(map);
    }
}
