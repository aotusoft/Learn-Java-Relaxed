package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rowsJson {
    public String rowsJson(Connection con) throws SQLException {
        PreparedStatement Pstmt =null;
        String sql = "select name as name,num as value from currenttable";
        Pstmt= con.prepareStatement(sql);
        ResultSet rs = Pstmt.executeQuery();

        return null;
    }
}
