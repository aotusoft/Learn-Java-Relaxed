package com.my.ann;

import org.junit.Test;
import org.springframework.stereotype.Repository;

/*注解Repository("userDao")
* 相当于XML文件中
* <bean id="userDao" class="com.my.ann.UDImp"/>
* */
@Repository("userDao")
public class UDImp implements UserDao {
    @Test
    @Override
    public void save() {
        System.out.println("由UserDao接口的save()方法输出");
    }
}
