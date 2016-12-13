package com.mySampleApplication.hibernate.dao;

import com.mySampleApplication.hibernate.db.tables.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by cdc89 on 11.12.2016.
 */
public class UserDao extends DaoA {
    public UserDao(){}

    public User findByLogin(String login){
        Criteria criteria = openCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.sqlRestriction("login = '"+login+"'"));
        User user= (User) criteria.uniqueResult();
        return user;
    }
}
