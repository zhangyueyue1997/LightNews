package com.pb.dao;

import com.pb.pojo.League;
import org.apache.ibatis.annotations.Param;

public interface LeagueDao {

    int saveFile(League league);
    League selectHtml();
}
