package com.my.pojo;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        CriteriaFind();
//        System.out.println("1 添加\t 2 修改\t 3 删除\t 4 查询\n其他键退出");
//        System.out.print("请输入：");
//        Scanner input = new Scanner(System.in);
//        String value = input.nextLine();
//        if (value.equals("1")) {
//            System.out.print("请输入用户名：");
//            String user = input.nextLine();
//            System.out.print("请输入密码：");
//            String pwd = input.nextLine();
//            addRow(user, pwd);
//            System.out.println("添加成功");
//        } else if (value.equals("2")) {
//            System.out.print("请输入UID：");
//            int uid = input.nextInt();
//            System.out.print("请输入用户名：");
//            String user = input.nextLine();
//            System.out.print("请输入密码：");
//            String pwd = input.nextLine();
//            updRow(uid, user, pwd);
//            System.out.println("修改成功");
//        } else if (value.equals("3")) {
//            System.out.print("请输入UID：");
//            int uid = input.nextInt();
//            delRow(uid);
//            System.out.println("删除成功");
//        } else if (value.equals("4")) {
//            System.out.print("请输入UID：");
//            int uid = input.nextInt();
//            findRow(uid);
////            queryFind();
//        } else {
//            System.exit(0);
//        }
    }

    public static void addRow(String user, String pwd) {
        //加载Hi配置文件
        Configuration cfg = new Configuration().configure();
        //使用SessionFactory
        SessionFactory sF = cfg.buildSessionFactory();
        //实例Session对象
        Session session = sF.openSession();
        //开始事务
        Transaction t = session.beginTransaction();
        //给表对应的实体对象的属性赋值
        UsertableEntity usertableEntity = new UsertableEntity(); //session 瞬时态/临时态
        usertableEntity.setUsername(user);
        usertableEntity.setPassword(pwd);
//        usertable.getCreatedate(Date.valueOf(LocalDate.now()));
        session.save(usertableEntity); //session 持久态
        t.commit();
        session.close(); //session 托管态
        sF.close();
    }

    public static void updRow(int uid, String user, String pwd) {
        //加载Hi配置文件
        Configuration cfg = new Configuration().configure();
        //使用SessionFactory
        SessionFactory sF = cfg.buildSessionFactory();
        //实例Session对象
        Session session = sF.openSession();
        //开始事务
        Transaction t = session.beginTransaction();
        //给表对应的实体对象的属性赋值
        UsertableEntity usertableEntity = (UsertableEntity) session.get(UsertableEntity.class, uid);
        usertableEntity.setUsername(user);
        usertableEntity.setPassword(pwd);
//        usertable.getCreatedate(Date.valueOf(LocalDate.now()));
        session.save(usertableEntity);
        t.commit();
        session.close();
        sF.close();
    }

    public static void delRow(int uid) {
        //加载Hi配置文件
        Configuration cfg = new Configuration().configure();
        //使用SessionFactory
        SessionFactory sF = cfg.buildSessionFactory();
        //实例Session对象
        Session session = sF.openSession();
        //开始事务
        Transaction t = session.beginTransaction();
        //给表对应的实体对象的属性赋值
        UsertableEntity usertableEntity = (UsertableEntity) session.get(UsertableEntity.class, uid);
        session.delete(usertableEntity);
        t.commit();
        session.close();
        sF.close();
    }

    public static void findRow(int uid) {
        //1.加载Hibernate核心配置文件
        Configuration cfg = new Configuration().configure();
        //2.使用sessionFactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用sessionfactory 实例化session对象
        Session session = sessionFactory.openSession();
        //4.开始事务
        Transaction transaction = session.beginTransaction();
        //5.对数据库进行操作
        UsertableEntity usertableEntity = (UsertableEntity) session.get(UsertableEntity.class, uid);
        System.out.println("ID:" + usertableEntity.getId());
        System.out.println("姓名:" + usertableEntity.getUsername());
        //6.commit提交事务
        transaction.commit();
        //7.close Database resources 关闭资源
        session.close();
        sessionFactory.close();
    }

    public static void queryFind() {
        //加载Hi配置文件
        Configuration cfg = new Configuration().configure();
        //使用SessionFactory
        SessionFactory sF = cfg.buildSessionFactory();
        //实例Session对象
        Session session = sF.openSession();
        //开始事务
        Transaction t = session.beginTransaction();

        //内存中的session
        String hql = "from UsertableEntity ";
        Query query = session.createQuery(hql);
        List<UsertableEntity> list = query.list();
        for (UsertableEntity user : list) {
            user.toString();
        }
        t.commit();
    }

    public static void CriteriaFind() {
        Configuration cfg = new Configuration().configure();
        SessionFactory sF = cfg.buildSessionFactory();
        Session session = sF.openSession();
        Transaction t = session.beginTransaction();
        //通过session获取criteria对象
        Criteria criteria = session.createCriteria(UsertableEntity.class);
//        criteria.add(Restrictions.eq("username","q"));
        criteria.add(Restrictions.eq("username","q"));
        List<UsertableEntity> list = criteria.list();
        for (UsertableEntity user:list){
            System.out.println(user.getUsername());
        }
        t.commit();
        session.close();
        sF.close();
    }
}
