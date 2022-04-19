package bean;

import bean.Currentnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class currentNumDao {
    //1.显示全部记录
	//2.显示列头
	//3.按名称查找
	//4.按编号查找
	        //1.全部数据
			public Object[][] currentNumRows(Connection con) throws SQLException {
			    PreparedStatement Pstmt=null;
				String sql="select bh,name,num from currenttable";
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
			public String rowsJson(Connection con)throws SQLException{
				PreparedStatement Pstmt=null;
				String sql="select name as name,num as value from currenttable";
				Pstmt=con.prepareStatement(sql);
				ResultSet rs=Pstmt.executeQuery();//返回结果集
				Currentnum Cn=new Currentnum();
				String  rows="";
				rs.beforeFirst();
				int i=0;
				while (rs.next()){
					i=i+1;
				}
				rs.beforeFirst();
				int j=1;
				while (rs.next()) {
				Cn.setNum(rs.getInt("value"));
				Cn.setName(rs.getString("name"));
				if (j!=i){
					rows=rows+Cn.toString()+",";

				}else{
					rows=rows+Cn.toString();
				}
				j=j+1;
				}
				return "{\"Tbdata\":["+rows+"]}";//返回所有行
			}
			//2.表格列头
			public String[] currentNumColumn(Connection con) throws SQLException {
			    PreparedStatement Pstmt=null;
				String sql="select bh as 编号,name as 名称,num as 数量  from currenttable where id=-1";
		        Pstmt= con.prepareStatement(sql);
		        ResultSet rs=Pstmt.executeQuery(); //返回的结果集
		        
		        String[] ColumnNames =new String[rs.getMetaData().getColumnCount()];
		    	for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {	  
		    		ColumnNames[i-1]=rs.getMetaData().getColumnLabel(i);
		    	}
			    return ColumnNames;//返回列头
			}
			//3.按编号查找
			public Object[][] searchBh(Connection con,String bh) throws SQLException {
				PreparedStatement Pstmt=null;
			    ResultSet rs=null;
				String sql="select bh,name,num from currenttable where bh=? "; //只返回一条
		        Pstmt= con.prepareStatement(sql);
		        Pstmt.setString(1,bh); //第一个参数赋值
		        rs=Pstmt.executeQuery(); //返回的结果集
		        
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
			//4.按名称查找
			public Object[][] searchName(Connection con,String name) throws SQLException {
				PreparedStatement Pstmt=null;
			    ResultSet rs=null;
				String sql="select bh,name,num from currenttable where name like \"%\"?\"%\"";//模糊查找
		        Pstmt= con.prepareStatement(sql);
		        Pstmt.setString(1,name); //第一个参数赋值
		        rs=Pstmt.executeQuery(); //返回的结果集
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
}
