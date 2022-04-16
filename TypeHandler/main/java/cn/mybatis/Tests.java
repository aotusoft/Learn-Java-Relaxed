package cn.mybatis;

import cn.mybatis.mapper.Mapper;
import cn.mybatis.pojo.Productinfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Tests {
    @Test
    public void create() throws IOException {
        InputStream iS = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sSFB = new SqlSessionFactoryBuilder();
        SqlSessionFactory sSF = sSFB.build(iS);
        //获取SqlSession        true事务自动提交
        SqlSession sS = sSF.openSession(true);
        //通过代理模式 创建Mapper接口的代理实现对象 (Mapper)
        Mapper mapper = sS.getMapper(Mapper.class);
        if (mapper.existTable() == 1) {
            System.out.println("TABLE ALREADY EXISTS");
        } else {
            mapper.createTable("ProductInfo");
            System.out.println("SUCCESS");
        }
    }
    @Test
    public void InsertInfo() throws Exception {
        InputStream iS = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sSFB = new SqlSessionFactoryBuilder();
        SqlSessionFactory sSF = sSFB.build(iS);
        //获取SqlSession        true事务自动提交
        SqlSession sS = sSF.openSession(true);
        //通过代理模式 创建UserMapper接口的代理实现对象 (userMapper)
        Mapper Mapper = sS.getMapper(Mapper.class);
        //执行userMapper里的方法
        Mapper.insertInfo("老坛酸菜",2.50F);
        Mapper.insertInfo("双汇火腿",1.00F);
        Mapper.insertInfo("瘦肉精",1000.00F);
        Mapper.insertInfo("敌敌畏",210.00F);
        Mapper.insertInfo("Samsung Galaxy Note7",5900.00F);
    }

    @Test
    public void QueryInfo() throws Exception {
        InputStream iS = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sSFB = new SqlSessionFactoryBuilder();
        SqlSessionFactory sSF = sSFB.build(iS);
        //获取SqlSession        true事务自动提交
        SqlSession sS = sSF.openSession(true);
        //通过代理模式 创建UserMapper接口的代理实现对象 (userMapper)
        Mapper Mapper = sS.getMapper(Mapper.class);
        //执行userMapper里的方法
        List<Productinfo> list = Mapper.Query(1F);
        for (Productinfo pI : list) {
            System.out.println(pI);
        }
    }
}
