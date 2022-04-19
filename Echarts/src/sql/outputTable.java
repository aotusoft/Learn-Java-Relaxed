package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class outputTable {
    /*
    *1.查看货物是否在流水表中存在
    *  通过名字查看货物是否存在
    *2.增加流水记录
    *3.取表格的列头
    *4.按时间查询表里的用户数据
    *5.显示全部记录
    //*6.删除流水记录
    //*7.修改流水记录
    //*8.按名称查询表里的用户数据
    //*9.按编号查询表里的用户数据
    */
    //1.查看货物是否存在
    public String searchInput(Connection con, String bh) throws SQLException {
        PreparedStatement Pstmt = null;
        ResultSet rs = null;
        String sql = "select id,name from currenttable where bh=? limit 1"; //只返回一条
        Pstmt = con.prepareStatement(sql);
        Pstmt.setString(1, bh); //第一个参数赋值
        rs = Pstmt.executeQuery(); //返回的结果集
        if (rs.next()) {  //如果不是空记录集，那么返回true，也就是找到了。
            return rs.getString(2);
        } else {
            return ""; //没有找到
        }
    }

    public String searchInputName(Connection con, String name) throws SQLException {
        PreparedStatement Pstmt = null;
        ResultSet rs = null;
        String sql = "select id from currenttable where name=? limit 1 "; //只返回一条
        Pstmt = con.prepareStatement(sql);
        Pstmt.setString(1, name); //第一个参数赋值
        rs = Pstmt.executeQuery(); //返回的结果集
        if (rs.next()) {  //如果不是空记录集，那么返回true，也就是找到了。
            return rs.getString(1);
        } else {
            return ""; //没有找到
        }
    }

    //2.增加流水记录
    public String add(Connection con, String bh, String name, int Rknum, String jhr, String jsr) throws SQLException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //System.out.println("当前时间为: " + ft.format(dNow));
        String SDate = ft.format(dNow);//取系统当前日期
        PreparedStatement Pstmt = null; //带参的SQL语句执行对象


        String sql = "select name from currenttable where bh=?"; //首先查看一下流水表是否有此编号
        Pstmt = con.prepareStatement(sql);
        Pstmt.setString(1, bh); // 物品编号
        ResultSet rs = Pstmt.executeQuery(); //返回记录集
        rs.beforeFirst();
        if (rs.next()) {//此编号存在
            if (!rs.getString(1).equals(name)) {//此编号已存在但是名字和现在的不一样
                return rs.getString(0);
            } else {//此编号存在并且名字也一致，那么新增一条入库记录，同时更新即时库存的数据
                //1.插入流水记录
                sql = "insert into outputTable (bh,name,num,jsr,createdate,kgName) values (?,?,?,?,?,?)";
                Pstmt = con.prepareStatement(sql);
                Pstmt.setString(1, bh); //第一个物品编号
                Pstmt.setString(2, name); //第二个物品名称
                Pstmt.setInt(3, Rknum); //第三物品入库数量
                Pstmt.setString(4, jhr); //第四交货人
                Pstmt.setString(5, SDate); //第六日期
                Pstmt.setString(6, jsr); //第六接收人
                int num = Pstmt.executeUpdate(); //返回的受影响的行数
                //2.更新库存记录
                sql = "update currenttable set num=num-? where bh= ?";
                Pstmt = con.prepareStatement(sql);
                Pstmt.setInt(1, Rknum); //第一个物品编号
                Pstmt.setString(2, bh); //第二个物品名称
                num = Pstmt.executeUpdate(); //返回的受影响的行数
                return String.valueOf(-1);
            }
        } else {//此编号不存在，那么新增一条入库记录，同时插入一条即时库存的数据
            //1.插入流水记录
            sql = "insert into outputTable (bh,name,num,jsr,createdate,kgName) values (?,?,?,?,?,?)";
            Pstmt = con.prepareStatement(sql);
            Pstmt.setString(1, bh); //第一个物品编号
            Pstmt.setString(2, name); //第二个物品名称
            Pstmt.setInt(3, Rknum); //第三物品入库数量
            Pstmt.setString(4, ""); //第四交货人
            Pstmt.setString(5, SDate); //第六日期
            Pstmt.setString(6, ""); //第六接收人
            int num = Pstmt.executeUpdate(); //返回的受影响的行数

            //2.插入出库存记录
            sql = "insert into currenttable (bh,name,num) values (?,?,?)";
            Pstmt = con.prepareStatement(sql);
            Pstmt.setString(1, bh); //第一个物品编号
            Pstmt.setString(2, name); //第二个物品名称
            Pstmt.setInt(3, Rknum); //第三物品入库数量
            num = Pstmt.executeUpdate(); //返回的受影响的行数
            return String.valueOf(-1);
        }
    }

    //3.全部出库数据
    public Object[][] inputRows(Connection con) throws SQLException {
        PreparedStatement Pstmt = null;
        String sql = "select bh,name,num,createdate from outputTable";
        Pstmt = con.prepareStatement(sql);
        ResultSet rs = Pstmt.executeQuery(); //返回的结果集

        ArrayList<ArrayList<Object>> RowsList = new ArrayList<ArrayList<Object>>();
        rs.beforeFirst();
        while (rs.next()) {
            ArrayList<Object> Row = new ArrayList<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                Row.add(rs.getObject(i));
            }
            RowsList.add(Row);
        }
        int m = RowsList.size();
        int n = rs.getMetaData().getColumnCount();
        Object[][] Rows = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Rows[i][j] = RowsList.get(i).get(j);
            }
        }
        return Rows;//返回所有行
    }

    //4.表格列头
    public String[] intputColumn(Connection con) throws SQLException {
        PreparedStatement Pstmt = null;
        String sql = "select bh as 编号,name as 名称,num as 数量,createdate as 出库日期 from outputTable where id=-1";
        Pstmt = con.prepareStatement(sql);
        ResultSet rs = Pstmt.executeQuery(); //返回的结果集

        String[] ColumnNames = new String[rs.getMetaData().getColumnCount()];
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            ColumnNames[i - 1] = rs.getMetaData().getColumnLabel(i);
        }
        return ColumnNames;//返回列头
    }

    //5.按日期范围查找
    public Object[][] inputRowsDate(Connection con, String Date1, String Date2) throws SQLException {
        PreparedStatement Pstmt = null;
        String sql = "select bh,name,num,createdate from outputTable where createdate between ? and ?";
        Pstmt = con.prepareStatement(sql);
        Pstmt.setString(1, Date1); //第一个 起始日期
        Pstmt.setString(2, Date2); //第二个 结束日期
        ResultSet rs = Pstmt.executeQuery(); //返回的结果集

        ArrayList<ArrayList<Object>> RowsList = new ArrayList<ArrayList<Object>>();
        rs.beforeFirst();
        while (rs.next()) {
            ArrayList<Object> Row = new ArrayList<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                Row.add(rs.getObject(i));
            }
            RowsList.add(Row);
        }
        int m = RowsList.size();
        int n = rs.getMetaData().getColumnCount();
        Object[][] Rows = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Rows[i][j] = RowsList.get(i).get(j);
            }
        }
        return Rows;//返回所有行
    }
}
