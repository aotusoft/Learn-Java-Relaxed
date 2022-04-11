package com.my.ann;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * 相当于配置文件中的
 * <bean id="userService" class="com.my.ann."/>
 * */
@Service("userService")
public class USImp implements UserService {
    @Resource(name="userDao")
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
