package com.dogecoin.cryptoapi.dao;

import com.dogecoin.cryptoapi.models.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserById(Integer userId);
}
