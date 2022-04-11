package com.my.auto;

import com.my.auto.UserDao;
import org.junit.Test;
import org.springframework.stereotype.Repository;

public class UDImp implements UserDao {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("自动装配 由UserDao接口的save()方法输出");
    }
}
