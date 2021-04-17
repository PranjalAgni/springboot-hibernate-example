package com.dogecoin.cryptoapi.dao;


import com.dogecoin.cryptoapi.models.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDaoImpl implements StatusDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer createStatus(Status status) {
        Session session = sessionFactory.getCurrentSession();
        session.save(status);
        return status.getStatusId();
    }
}
