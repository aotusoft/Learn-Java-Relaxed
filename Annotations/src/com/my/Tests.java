package com.my;


import com.my.ann.UserAction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tests {
    public void Test() {
        String XmlFile = "beansAnnAuto.xml";
        ApplicationContext aC = new ClassPathXmlApplicationContext(XmlFile);
        UserAction uA = (UserAction) aC.getBean("userAction");
        uA.save();
    }
    @Test
    public void Testy() {
        String XmlFile = "beans.xml";
        ApplicationContext aC = new ClassPathXmlApplicationContext(XmlFile);
        com.my.auto.UserAction uA = (com.my.auto.UserAction) aC.getBean("userAction");
        uA.save();

    }
    @Test
    public void Testz() {
        String XmlFile = "beansAnnAuto.xml";
        ApplicationContext aC = new ClassPathXmlApplicationContext(XmlFile);
        com.my.annAuto.UserAction uA = aC.getBean("userAction",com.my.annAuto.UserAction.class);
        uA.save();

    }
}
