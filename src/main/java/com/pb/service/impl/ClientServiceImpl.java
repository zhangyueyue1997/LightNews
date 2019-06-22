package com.pb.service.impl;

import com.pb.common.vo.PageObject;
import com.pb.dao.ClientDao;
import com.pb.pojo.Client;
import com.pb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao dao;

    @Override
    public List<Client> getClientsByUsernamePassword(Client entity) {
        return dao.selClientByUsernamePassword(entity);
    }

    @Override
    public PageObject<Client> getClientsByPage(Integer pageCurrent) {
        int pageSize = 8;//设置单页显示的数据条目数为3.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Client> records = dao.selClientByPage(startIndex, pageSize);//获取数据
        int rowCount = dao.selRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Client> obj = new PageObject<>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)
    }

    @Override
    public List<Client> getClientPageByFuzzyName(Client entity) {
        return dao.selClientPageByFuzzyName(entity);
    }


    @Override
    @Transactional
    public int save(Client entity) {
        int result = 1;
        try {
            dao.insertNewClient(entity);
        } catch (DataAccessException e) {
            result = 0;
        }
        return result;
    }
}
