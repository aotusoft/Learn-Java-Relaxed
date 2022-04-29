package com.my.hello;

import org.junit.Test;
import static junit.framework.Assert.*;
public class HelloTest{
    @Test
    public void HelloClassTest(){
        Hello hello = new Hello();
        String res = hello.sayHello("Gu");
        System.out.println(res);
    }
}
