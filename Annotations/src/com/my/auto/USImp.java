package com.my.auto;

import com.my.auto.UserDao;


public class USImp implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        this.userDao.save();
        System.out.println("");
    }
}
