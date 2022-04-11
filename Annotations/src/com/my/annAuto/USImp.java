package com.my.annAuto;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * 相当于配置文件中的
 * <bean id="userService" class="com.my.ann."/>
 * */
public class USImp implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        this.userDao.save();
        System.out.println("");
    }
}
