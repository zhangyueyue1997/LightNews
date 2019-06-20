package com.pb.service.impl;

import com.pb.dao.MessageDao;
import com.pb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

   @Autowired
   private MessageDao messageDao;

    @Override
    public int updateMessage(String html) {
       return messageDao.updateMessage(html);
    }

    @Override
    public String selectMessage() {
        return messageDao.selectMessage();
    }
}
