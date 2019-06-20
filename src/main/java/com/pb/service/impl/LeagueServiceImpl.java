package com.pb.service.impl;

import com.pb.dao.LeagueDao;
import com.pb.pojo.League;
import com.pb.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueServiceImpl implements LeagueService {
    @Autowired
    private LeagueDao leagueDao;

    @Override
    public int saveFlie(League league) {
       return leagueDao.saveFile(league);

    }

    @Override
    public League selectHtml() {
        return leagueDao.selectHtml();
    }
}
