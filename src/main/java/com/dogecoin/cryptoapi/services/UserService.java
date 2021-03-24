package com.dogecoin.cryptoapi.services;

import com.dogecoin.cryptoapi.dao.UserDao;
import com.dogecoin.cryptoapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Transactional
    public Integer addUser(String name, String email, String bio) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBio(bio);
        return userDao.createUser(user);
    }
}
