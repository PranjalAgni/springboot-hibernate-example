package com.dogecoin.cryptoapi.controllers;

import com.dogecoin.cryptoapi.models.User;
import com.dogecoin.cryptoapi.services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @GetMapping("/getAllUsers")
    public String getUsers() {
        logger.info("Here inside getUsers");
        HashMap<String,Object> map = new HashMap<>();
        List<User> userList = userService.getUsers();
        Gson gson = new Gson();

        map.put("count", userList.size());
        map.put("users", userList);
        return gson.toJson(map);
    }


    @GetMapping("/getUserById/{id}")
    public HashMap<String, Object> getUserById(@PathVariable int id) {
        logger.info("Here inside getUserById: " + id);
        User user = userService.getUserById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

    @PostMapping("/create")
    public HashMap<String, Object> createUser(@RequestBody String data) {
        logger.info("Inside createUser()");
        logger.info(data.toString());
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String email = jsonObject.get("email").getAsString();
        String bio = jsonObject.get("bio").getAsString();
        Integer id = userService.addUser(name, email, bio);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("id", id);
        return map;
    }

}
