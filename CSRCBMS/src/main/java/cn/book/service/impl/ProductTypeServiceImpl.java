package cn.book.service.impl;

import cn.book.mapper.ProductTypeMapper;
import cn.book.pojo.ProductType;
import cn.book.pojo.ProductTypeExample;
import cn.book.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper typeMapper;

    @Override
    public List<ProductType> getAll() {
        return typeMapper.selectByExample(new ProductTypeExample());
    }
}
