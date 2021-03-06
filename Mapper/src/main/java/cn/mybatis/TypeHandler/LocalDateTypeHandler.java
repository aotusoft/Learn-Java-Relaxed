package cn.mybatis.TypeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDate localDate, JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i,Date.valueOf(localDate));
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Date date = resultSet.getDate(s);
        if (date == null) {

        }
        return null;
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Date date = resultSet.getDate(i);
        if (date == null) {
            return null;
        } else {
            return  date.toLocalDate();
        }
    }

    @Override
    public LocalDate getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Date date = callableStatement.getDate(i);
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate();
        }
    }
}
