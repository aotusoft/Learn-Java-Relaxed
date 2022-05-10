package cn.book.service;

import com.github.pagehelper.PageInfo;
import cn.book.pojo.ProductInfo;
import cn.book.pojo.vo.ProductinfoVo;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询所有下商品
     * @return
     */
    List<ProductInfo> getAll();

    /**
     * 分页功能实现
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo spiltPage(int pageNum,int pageSize);

    /**
     * 增加商品
     * @param info
     * @return
     */
    int save(ProductInfo info);

    /**
     * 按照主键ID查询商品
     */
    ProductInfo getByTd(int pid);

    /**
     * 跟新商品
     * @param info
     * @return
     */
    int update(ProductInfo info);

    /**
     * 单个商品的删除
     */
    int delete(int pid);

    /**
     * 批量删除商品
     */
    int deleteBeach(String []ids);

    /**
     * 多条件商品查询
     */
    List<ProductInfo> selectCondition(ProductinfoVo productinfoVo);

    /**
     * 多条件查询分页
     */
    public PageInfo splitPageVo(ProductinfoVo vo,int pageSize);

}
