package com.my.xml;

import com.my.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public void test() {
        String XmlFile = "beans.xml";
        ApplicationContext aC = new ClassPathXmlApplicationContext(XmlFile);
        UserDao userDao = aC.getBean("");
    }
}
