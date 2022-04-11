package com.my.dao;

import org.junit.Test;

public class UDImp implements UserDao{
    @Test
    @Override
    public void save() {
        System.out.println("\033[36m" + "实现了保存方法");
        //人为制造异常 引发异常通知
//        int a = 1/0;
    }
}
