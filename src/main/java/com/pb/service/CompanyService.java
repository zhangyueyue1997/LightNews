package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.Company;

/***
 * 业务层接口用于对接控制层（Controller）和持久层（dao）
 * @author zgl
 *
 */
public interface CompanyService {
    /**（可以根据公司名）查找页面信息*/
    PageObject<Company> findPageObjects(String name, Integer pageCurrent);
    /**根据id值删除对象*/
    int deleteObjects(String[] ids);
    /**保存公司*/
    int saveObjects(Company entity);
    /**更新公司信息*/
    int updateObjects(Company entity);
    /**根据id查找数据*/
    Company findObjectById(Integer id);
    /**添加公司信息*/
    int updateCompanyByOpenid(Company company);
    /**回显公司资料*/
    Company selectNameAndIntroduce(String openid);
}
