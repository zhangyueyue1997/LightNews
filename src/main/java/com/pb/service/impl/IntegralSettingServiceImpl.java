package com.pb.service.impl;

import com.pb.dao.IntegralSettingDao;
import com.pb.service.IntegralSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegralSettingServiceImpl implements IntegralSettingService {

    @Autowired
    private IntegralSettingDao dao;

    @Override
    public int updateDefaultIntegral(int integral) {
        return dao.updateDefaultIntegral(integral);
    }

    @Override
    public int selDefaultIntegral() {
        return dao.selDefaultIntegral();
    }

    @Override
    public int updatePrewIntegral(int integral) {
        return dao.updatePrewIntegral(integral);
    }

    @Override
    public int selPrewIntegral() {
        return dao.selPrewIntegral();
    }

    @Override
    public int updateDownloadIntegral(int integral) {
        return dao.updateDownloadIntegral(integral);
    }

    @Override
    public int selDownloadIntegral() {
        return dao.selDownloadIntegral();
    }

    @Override
    public int updatePublishIntegral(int integral) {
        return dao.updatePublishIntegral(integral);
    }

    @Override
    public int selPublishIntegral() {
        return dao.selPublishIntegral();
    }

    @Override
    public int updateReferIntegral(int integral) {
        return dao.updateReferIntegral(integral);
    }

    @Override
    public int selReferIntegral() {
        return dao.selReferIntegral();
    }
}
