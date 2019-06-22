package com.pb.dao;

import com.pb.pojo.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClientDao {
    List<Client> selClientByUsernamePassword(Client entity);

    List<Client> selClientByPage(@Param("startIndex") Integer startIndex,
                                 @Param("pageSize") Integer pageSize);

    List<Client> selClientPageByFuzzyName(Client entity);

    Integer selRowCount();

    int insertNewClient(Client entity) throws DataAccessException;
}
