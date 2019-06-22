package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.Client;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface ClientService {
    List<Client> getClientsByUsernamePassword(Client entity);

    PageObject<Client> getClientsByPage(Integer pageCurrent);

    List<Client> getClientPageByFuzzyName(Client entity);

    int save (Client entity);
}
