package com.shed.dao.impl;

import com.shed.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void print() {
        System.out.println("[UserDaoImpl]执行SQL语句...");
    }
}
