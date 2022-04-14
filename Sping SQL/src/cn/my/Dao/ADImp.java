package cn.my.Dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ADImp implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void out(String outUser, int money) {
        this.jdbcTemplate.update("update account set money=money-? where Name=?", money, outUser);
    }

    @Override
    public void in(String inUser, int money) {
        this.jdbcTemplate.update("update account set money=money+? where Name=?", money, inUser);
    }
}
