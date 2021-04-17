package com.dogecoin.cryptoapi.services;

import com.dogecoin.cryptoapi.dao.StatusDao;
import com.dogecoin.cryptoapi.models.Status;
import com.dogecoin.cryptoapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("statusService")
public class StatusService {
    @Autowired
    StatusDao statusDao;

    @Transactional
    public Integer addStatus(String statusName, Integer userId) {
        User user = new User();
        user.setId(userId);
        Status status = new Status();
        status.setUser(user);
        status.setName(statusName);
        return  statusDao.createStatus(status);
    }
}
