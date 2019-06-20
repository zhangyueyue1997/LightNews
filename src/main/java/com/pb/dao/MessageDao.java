package com.pb.dao;

import org.apache.ibatis.annotations.Param;

public interface MessageDao {


    int updateMessage(@Param("html") String html);

    String selectMessage();

}
