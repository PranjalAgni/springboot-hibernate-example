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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
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

    //https://github.com/java2blog/Tutorials/blob/master/Spring%20Boot/SpringBootHibernateExample/src/main/java/org/arpit/java2blog/dao/CustomerDaoImpl.java
}
