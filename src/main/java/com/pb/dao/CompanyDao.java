package com.pb.dao;

import com.pb.pojo.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 对应mapper文件的持久（dao）层数据
 * @author zgl
 *
 */
public interface CompanyDao {
    /**查找页面信息*/
    List<Company> findPageObjects(
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
    /**获取总记录数*/
    int getRowCount();
    /**根据选择的id删除数据*/
    int deleteObjects(@Param("ids")String[] ids);
    /**添加数据*/
    int saveObjects(Company entity);
    /**更新数据信息*/
    int updateObjects(Company entity);
    /**根据id查找数据*/
    Company findObjectById(@Param("id")Integer id);
    /**添加公司信息*/
    int updateCompanyByOpenid(Company company);
    /**回显公司资料*/
    Company selectNameAndIntroduce(@Param("openid") String openid);

    /***
     * 根据Openid 查找公司信息
     * @param openid 微信唯一用户标识
     * @return 查询到的Company
     */
    Company findObjectByOpenid(@Param("openid") String openid);

}
