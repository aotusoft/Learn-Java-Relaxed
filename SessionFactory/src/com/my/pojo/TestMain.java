package com.my.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;

public class TestMain {
    public static void main(String[] args) {
        addRow();
//        updateRow();
//        findRow(30L);
//        deleteRow(26L);
//        QueryFind("from Usertable where username like '李%' ");
    }
    public static void addRow(){
        //1.加载Hibernate核心配置文件
        Configuration cfg = new Configuration().configure();
        //2.使用sessionFactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用sessionfactory 实例化session对象
        Session session = sessionFactory.openSession();
        //4.开始事务
        Transaction transaction = session.beginTransaction();
        //给表对应的实体对象属性赋值
        UsertableEntity usertableEntity = new UsertableEntity();
        //usertableEntity.setId(117);//找到Id为117的记录
        usertableEntity.setUsername("张三");
        usertableEntity.setPassword("123456");
        usertableEntity.setCreateuser("王五");
        usertableEntity.setCreatedate(Date.valueOf(LocalDate.now()));
        //5.调用session保存数据
        session.save(usertableEntity);//将数据存储到表中
        //6.commit提交事务
        transaction.commit();
        //7.close Database resources 关闭资源
        session.close();
        sessionFactory.close();
    }
    public static void findRow(long uid){
        //1.加载Hibernate核心配置文件
        Configuration cfg = new Configuration().configure();
        //2.使用sessionFactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用sessionfactory 实例化session对象
        Session session = sessionFactory.openSession();
        //4.开始事务
        Transaction transaction = session.beginTransaction();
        //5.对数据库进行操作
        Usertable usertable = (Usertable)session.get(Usertable.class,uid);
        System.out.println("ID:" + usertable.getId());
        System.out.println("姓名:" + usertable.getUsername());
        //6.commit提交事务
        transaction.commit();
        //7.close Database resources 关闭资源
        session.close();
        sessionFactory.close();
    }
    public static void deleteRow(long uid) {
        //1.加载Hibernate核心配置文件
        Configuration cfg = new Configuration().configure();
        //2.使用sessionFactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用sessionfactory 实例化session对象
        Session session = sessionFactory.openSession();
        //4.开始事务
        Transaction transaction = session.beginTransaction();
        //5.对数据库进行操作
        Usertable usertable = (Usertable)session.get(Usertable.class,uid);
        session.delete(usertable);
        //6.commit提交事务
        transaction.commit();
        //7.释放资源
        session.close();
        sessionFactory.close();
    }
    public static void QueryFind(){
        //1.加载Hibernate核心配置文件
        Configuration cfg = new Configuration().configure();
        //2.使用sessionFactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用sessionfactory 实例化session对象
        Session session = sessionFactory.openSession();
        //4.开始事务
        Transaction transaction = session.beginTransaction();
        //5.操作数据库进行查询
        //5.1编写HQL
        String hql="";
        //5.2调用session.createQuery(hql);
        Query query =session.createQuery(hql);

    }
}
