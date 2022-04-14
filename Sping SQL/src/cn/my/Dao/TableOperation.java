package cn.my.Dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableOperation {
    public void create(String TableName) {
        ApplicationContext aC = new ClassPathXmlApplicationContext("beans.xml");
        JdbcTemplate jT = aC.getBean("jdbcTemplate", JdbcTemplate.class);
        jT.execute("CREATE TABLE if not exists " + TableName + "(Id int primary key auto_increment,Name varchar(25) not null,money int DEFAULT null)");
        jT.execute("insert into " + TableName + "(name,money) value ('jack',1000)");
        jT.execute("insert into " + TableName + "(name,money) value ('rose',1000)");
    }
}
