package com.dogecoin.cryptoapi.controllers;

import com.dogecoin.cryptoapi.models.User;
import com.dogecoin.cryptoapi.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @GetMapping("/getAllUsers")
    public HashMap<String, Object> getUsers() {
        logger.info("Here inside getUsers");
        HashMap<String,Object> map = new HashMap<>();
        List<User> userList = userService.getUsers();
        map.put("count", userList.size());
        map.put("users", userList);
        return map;
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
    public HashMap<String, Object> createUser(@RequestBody JsonNode data) {
        logger.info("Inside createUser()");
        logger.info(data.toString());
        String name = data.get("name").toString();
        String email = data.get("email").toString();
        String bio = data.get("bio").toString();
        Integer id = userService.addUser(name, email, bio);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("id", id);
        return map;
    }

}
