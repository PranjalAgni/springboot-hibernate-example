package com.dogecoin.cryptoapi.dao;

import com.dogecoin.cryptoapi.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User").list();
        return userList;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        String getUserByIdQueryString = "FROM User user WHERE user.id = :userId";
        Query getUserByIdQuery = session.createQuery(getUserByIdQueryString);
        getUserByIdQuery.setParameter("userId", userId);
        List<User> userList = getUserByIdQuery.list();
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public Integer createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user.getId();
    }
}
