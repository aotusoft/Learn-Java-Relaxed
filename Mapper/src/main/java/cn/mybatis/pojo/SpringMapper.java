package cn.mybatis.pojo;

import cn.mybatis.pojo.Spring;
import java.util.List;

public interface SpringMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring
     *
     * @mbg.generated Fri Apr 01 09:38:15 CST 2022
     */
    int deleteByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring
     *
     * @mbg.generated Fri Apr 01 09:38:15 CST 2022
     */
    int insert(Spring record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring
     *
     * @mbg.generated Fri Apr 01 09:38:15 CST 2022
     */
    Spring selectByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring
     *
     * @mbg.generated Fri Apr 01 09:38:15 CST 2022
     */
    List<Spring> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring
     *
     * @mbg.generated Fri Apr 01 09:38:15 CST 2022
     */
    int updateByPrimaryKey(Spring record);
}