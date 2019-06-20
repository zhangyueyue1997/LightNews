package com.pb.service.impl;

import com.pb.common.vo.PageObject;
import com.pb.dao.CompanyDao;
import com.pb.pojo.Company;
import com.pb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * 业务层的具体实现类
 * @author Yang
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao dao = null;

    @Override
    public PageObject<Company> findPageObjects(String name, Integer pageCurrent) {
        int pageSize = 8;//设置单页显示的数据条目数为3.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Company> records = dao.findPageObjects(startIndex, pageSize);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Company> obj = new PageObject<Company>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)
    }

    @Override
    public int deleteObjects(String[] ids) {
        return dao.deleteObjects(ids);
    }

    @Override
    public int saveObjects(Company entity) {
        return dao.saveObjects(entity);
    }

    @Override
    public int updateObjects(Company entity) {
        return dao.updateObjects(entity);
    }

    @Override
    public Company findObjectById(Integer id) {
        return dao.findObjectById(id);
    }

    @Override
    public int updateCompanyByOpenid(Company company) {
        return dao.updateCompanyByOpenid(company);
    }

    @Override
    public Company selectNameAndIntroduce(String openid) {
        return dao.selectNameAndIntroduce(openid);
    }
}
