package sql;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class InitDatabase {
    /**
     * 创建c3p0连接池的步骤
     * c3p0通过XML文件来配置连接池
     * 1.先配置c3p0-config.xml文件
     * 2.将文件放到src文件夹下。
     * 3.定义一个ComboPooledDataSource 连接池变量
     * 4。定义一个Connection 连接变量
     * 5.指定数据库驱动程序
     * 6.创建数据库连接池对象给上面定义的ComboPooledDataSource变量
     * 7.创建Connection对象给上面定义的Connection变量
     */
    //一.创建一个全局的Connection对象
    public Connection con = null;

    //二.定义连接方法
    public Connection getConnection() {
        ComboPooledDataSource cpds = null;
        Connection conn = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            String URL = "jdbc:mysql://localhost:3306/manager?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            //通过连接池获取数据库连接
            cpds = new ComboPooledDataSource();//创建出连接池
//            conn=cpds.getConnection();//获取连接对象
            con = DriverManager.getConnection(URL, "root", "123456");
            System.out.println("数据库连接创建成功！");
        } catch (Exception e) {
            System.out.println("数据库连接创建失败！");
            e.printStackTrace();
            System.exit(0);
        }
        return con;
    }

    //三.定义释放资源的方法
    public void closeAll(Connection conn) throws SQLException {
        if ((conn != null) || (!conn.isClosed())) {
            conn.close();
        }
    }

    //四.定义创建数据库的方法
    public void CreateDataBase() throws SQLException {
        String sql = "create  database IF NOT EXISTS testkc"; //如果不存在的话那就创建数据库
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.execute("use manager");
    }

    //五.创建数据库中的表
    public void CrateTable() throws SQLException {
        Statement stmt = this.con.createStatement();
        stmt.execute("use manager"); //切换数据库
        //执行建表语句
        //1.创建用户表
        String sql = "create  table IF NOT EXISTS userTable(" +
                "id int primary key auto_increment," +
                "username varchar(20)," +
                "password varchar(10)," +
                "createuser varchar(20)," +
                "createdate date" +
                ")"; //如果不存在的话那就创建数据库
        stmt.execute(sql);
        sql = "select id from userTable where username='Super'";//创建一个默认用户
        ResultSet rs = stmt.executeQuery(sql);
        if (!rs.next()) { //为空，那写入一个超级用户，也就是系统的默认目录
            sql = "insert into userTable (username,password,createuser,createdate) values ('Super','123456','Super','2021-11-09')";
            stmt.execute(sql);
        }
        //2.创建入库流水表
        sql = "create  table IF NOT EXISTS inputTable(" +
                "id int primary key auto_increment," +
                "bh varchar(16)," +
                "name varchar(30)," +
                "num int," +
                "jhr varchar(8)," +
                "jsr varchar(8)," +
                "createdate date" +
                ")"; //如果不存在的话那就创建数据库
        stmt.execute(sql);

        //3.出库流水表、
        sql = "create  table IF NOT EXISTS outputTable(" +
                "id int primary key auto_increment," +
                "bh varchar(16)," +
                "name varchar(30)," +
                "num int," +
                "jsr varchar(8)," +
                "kgName varchar(8)," +
                "createdate date" +
                ")"; //如果不存在的话那就创建数据库
        stmt.execute(sql);
        //4.创建即时库存表
        sql = "create  table IF NOT EXISTS currenttable(" +
                "id int primary key auto_increment," +
                "bh varchar(16)," +
                "name varchar(30)," +
                "num  int" +
                ")"; //如果不存在的话那就创建数据库
        stmt.execute(sql);
    }

    public static void main(String[] args) throws SQLException {
        InitDatabase idb = new InitDatabase();//创建数据库初始化对象
        idb.con = idb.getConnection(); //创建连接
        idb.CrateTable(); //创建表
        userTable utd = new userTable();

//	    if (utd.searchUser(idb.con,"李四")) {
//	    	System.out.println("有李四");
//	    }else {
//	    	System.out.println("没有名为李四的用户");
//	    	utd.addUser(idb.con, "李四", "123456", "authon");
//	    };
//	    if (utd.DlYz(idb.con, "Super", "123456")) {
//	    	System.out.println("登录成功！");
//	    }else {
//	    	System.out.println("登录失败！");
//	    }
//	    utd.userRows(idb.con);
    }
}
