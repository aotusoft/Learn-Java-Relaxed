package com.my.annAuto;

import org.junit.Test;
import org.springframework.stereotype.Repository;

public class UDImp implements UserDao {
    @Override
    public void save() {
        System.out.println("由UserDao接口的save()方法输出");
    }
}
