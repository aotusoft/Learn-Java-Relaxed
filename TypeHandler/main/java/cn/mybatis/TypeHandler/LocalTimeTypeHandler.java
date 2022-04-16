package cn.mybatis.TypeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalTime;

public class LocalTimeTypeHandler implements TypeHandler<LocalTime> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, LocalTime localTime, JdbcType jdbcType) throws SQLException {
        if (localTime == null) {
            preparedStatement.setTime(i, null);
        } else {
//            将localtime转换为java.sql.Time
            preparedStatement.setTime(i, Time.valueOf(localTime));
        }
    }

    @Override
    public LocalTime getResult(ResultSet resultSet, String s) throws SQLException {
        Time time = resultSet.getTime(s);
        return time == null ? null : time.toLocalTime();
    }

    @Override
    public LocalTime getResult(ResultSet resultSet, int i) throws SQLException {
        Time time = resultSet.getTime(i);
        return time == null ? null : time.toLocalTime();
    }

    @Override
    public LocalTime getResult(CallableStatement callableStatement, int i) throws SQLException {
        Time time = callableStatement.getTime(i);
        return time == null ? null : callableStatement.getTime(i).toLocalTime();
    }
}
