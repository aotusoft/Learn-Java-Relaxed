package cn.mybatis.mapper;

import cn.mybatis.pojo.Productinfo;

import java.util.List;

public interface Mapper {
    int existTable();

    void createTable(String tableName);

    void insertInfo(String name, float price);

    List<Productinfo> Query(float price);
}

