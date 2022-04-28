package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class userTable {
    /*
     *1.查看用户是否存在
     *2.增加用户
     *3.删除用户
     *4.修改密码
     *5.取表格的列头
     *6.全部表里的用户数据
     *7.验证登录是否成功
     */
    //1.查看用户是否存在
    public boolean searchUser(Connection con,String userName) throws SQLException {
        PreparedStatement Pstmt=null;
        ResultSet rs=null;
        String sql="select id from userTable where username=?";
        Pstmt= con.prepareStatement(sql);
        Pstmt.setString(1,userName); //第一个参数赋值
        rs=Pstmt.executeQuery(); //返回的结果集
        if (rs.next()) {  //如果不是空记录集，那么返回true，也就是找到了。
            return true;
        }else {
            return false; //没有名为李四的用户
        }
    }
    //2.增加用户
    public int addUser(Connection con,String userName,String password,String createuser) throws SQLException {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        //System.out.println("当前时间为: " + ft.format(dNow));
        String SDate= ft.format(dNow);//取系统当前日期
        PreparedStatement Pstmt=null; //带参的SQL语句执行对象
        String sql="insert into userTable (username,password,createuser,createdate) values (?,?,?,?)";
        Pstmt= con.prepareStatement(sql);
        Pstmt.setString(1,userName); //第一个参数赋值
        Pstmt.setString(2,password); //第一个参数赋值
        Pstmt.setString(3,createuser); //第一个参数赋值
        Pstmt.setString(4,SDate); //第一个参数赋值
        int num=Pstmt.executeUpdate(); //返回的受影响的行数
        return num;
    }
    //3.删除用户
    public int deleteUser(Connection con,String userName) throws SQLException {
        PreparedStatement Pstmt=null;
        String sql="delete  from  userTable where username=?";
        Pstmt= con.prepareStatement(sql);
        Pstmt.setString(1,userName); //第一个参数赋值s
        int num=Pstmt.executeUpdate(); //返回的结果集
        return num;
    }
    //4.修改密码
    public int  Modifypwd(Connection con,String userName,String PassWord) throws SQLException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        //System.out.println("当前时间为: " + ft.format(dNow));
        String SDate= ft.format(dNow);

        PreparedStatement Pstmt=null;
        String sql="update userTable set password=? where username=?";
        Pstmt= con.prepareStatement(sql);
        Pstmt.setString(1,PassWord); //第一个参数赋值
        Pstmt.setString(2,userName); //第二个参数赋值
        int num=Pstmt.executeUpdate(); //返回的结果集
        return num;
    }
    //6.全部用户数据
    public Object[][] userRows(Connection con) throws SQLException {
        PreparedStatement Pstmt=null;
        String sql="select id,username,createuser,createdate from userTable";
        Pstmt= con.prepareStatement(sql);
        ResultSet rs=Pstmt.executeQuery(); //返回的结果集

        ArrayList <ArrayList<Object>> RowsList =new ArrayList<ArrayList<Object>>();
        rs.beforeFirst();
        while (rs.next()) {
            ArrayList <Object> Row =new ArrayList<Object>();
            for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                Row.add(rs.getObject(i));
            }
            RowsList.add(Row);
        }
        int m=RowsList.size();
        int n=rs.getMetaData().getColumnCount();
        Object[][] Rows =new Object[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                Rows[i][j]=RowsList.get(i).get(j);
            }
        }
        return Rows;//返回所有行
    }
    //7.表格列头
    public String[] userColumn(Connection con) throws SQLException {
        PreparedStatement Pstmt=null;
        String sql="select id as id,username as 用户名,createuser as 创建人,createdate as 创建日期 from userTable where id=-1";
        Pstmt= con.prepareStatement(sql);
        ResultSet rs=Pstmt.executeQuery(); //返回的结果集

        String[] ColumnNames =new String[rs.getMetaData().getColumnCount()];
        for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
            ColumnNames[i-1]=rs.getMetaData().getColumnLabel(i);
        }
        return ColumnNames;//返回列头
    }
    //8.验证登录
    public boolean DlYz(Connection con,String userName,String PassWord) throws SQLException {

        PreparedStatement Pstmt=null;
        String sql="select id  from  userTable where  username=? and password=?";
        Pstmt= con.prepareStatement(sql);
        Pstmt.setString(1,userName); //第一个参数赋值
        Pstmt.setString(2,PassWord); //第二个参数赋值
        ResultSet rs=Pstmt.executeQuery(); //返回的结果集
        if (rs.next()) {
            return true; //说明找到了此条记录，输入的用户名和密码是对应的
        }else {
            return false;//说明输入用户名和密码不一致
        }
    }
}














