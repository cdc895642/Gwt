package com.mySampleApplication.hibernate.service;

import com.mySampleApplication.hibernate.dao.UserDao;
import com.mySampleApplication.hibernate.db.tables.User;

/**
 * Created by cdc89 on 11.12.2016.
 */
public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao=new UserDao();
    }

    public User findByLogin(String login){
        userDao.openCurrentSession();
        User user=userDao.findByLogin(login);
        userDao.closeCurrentSession();
        return user;
    }
}
