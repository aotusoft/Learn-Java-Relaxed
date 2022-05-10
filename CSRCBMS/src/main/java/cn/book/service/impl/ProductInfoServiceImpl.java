package cn.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.book.mapper.ProductInfoMapper;
import cn.book.pojo.ProductInfo;
import cn.book.pojo.ProductInfoExample;
import cn.book.pojo.vo.ProductinfoVo;
import cn.book.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    /**
     * 业务逻辑层一定有数据访问层
     */
    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    /**
     * 分页功能
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo spiltPage(int pageNum, int pageSize) {
        //设置当前页，条数
        PageHelper.startPage(pageNum,pageSize);
        //进行pageInfo封装
        ProductInfoExample example = new ProductInfoExample();
        //设置排序，按照主键降序排序
        example.setOrderByClause("p_id desc");
        //设置排序取集合
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByTd(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBeach(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductinfoVo productinfoVo) {
        return productInfoMapper.selectCondition(productinfoVo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductinfoVo vo, int pageSize) {
        //取出集合之前设置PageHelper.startPage()
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

}
