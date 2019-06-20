package com.pb.service.impl;

import com.pb.common.vo.PageObject;
import com.pb.dao.CompanyDao;
import com.pb.dao.WxUserDao;
import com.pb.pojo.Company;
import com.pb.pojo.UCompany;
import com.pb.pojo.WxUser;
import com.pb.service.UserWithCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWithCompanyServiceImpl implements UserWithCompanyService {

    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private CompanyDao companyDao;

    /***
     * 根据推荐人和当前页数查找信息
     * @param pageCurrent 当前页
     * @param referrer 推荐人Openid
     * @return 结果集
     */
    @Override
    public PageObject<UCompany> findObjectsByPage(Integer pageCurrent,String referrer) {
        //设置页大小
        int pageSize = 8;
        int startPage = (pageCurrent - 1) * pageSize;

        List<WxUser> usersList = wxUserDao.findPageObjectsByRefer(referrer,startPage,pageSize);
        int rowCount = usersList.size();
        List<UCompany> records = new ArrayList<>();
        int pageCount = rowCount / pageSize; //计算获得总页数
        //遍历整个列表
        for (WxUser user : usersList) {

            UCompany userWithCompany = new UCompany();
            //写入微信用户信息
            userWithCompany.setUser(user);

            String openid = user.getOpenid();
            //根据openid 查找所在公司
            Company company = companyDao.findObjectByOpenid(openid);
            //写入用户所在公司信息
            userWithCompany.setCompany(company);
            //添加到结果集
            records.add(userWithCompany);
        }
        //设置分页Bean
        PageObject<UCompany> uCompanyList = new PageObject<>();
        uCompanyList.setRowCount(rowCount);
        uCompanyList.setRecords(records);
        uCompanyList.setPageCount(pageCount);
        uCompanyList.setPageCurrent(pageCurrent);
        //返回结果Bean
        return uCompanyList;
    }

    /***
     * 根据openid查找企业所有者及其企业相关信息
     * @param openid 所有人Openid
     * @return 结果集
     */
    @Override
    public UCompany findObjectByOpenid(String openid) {
        UCompany userWithCompany = new UCompany();

        Company myCompany = companyDao.findObjectByOpenid(openid);
        WxUser Owner = wxUserDao.findObjectById(openid);
        userWithCompany.setCompany(myCompany);
        userWithCompany.setUser(Owner);
        return userWithCompany;
    }
}
