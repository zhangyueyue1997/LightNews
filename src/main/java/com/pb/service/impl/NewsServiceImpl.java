package com.pb.service.impl;

import com.pb.common.Utils.FileHelper;
import com.pb.common.vo.PageObject;
import com.pb.dao.NewsDao;
import com.pb.pojo.News;
import com.pb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;
    @Override
    public int insertNews(News news) {
       return newsDao.insertNews(news);
    }

    @Override
    public PageObject<News> selectNews(Integer CurrentPage,Integer type,String title) {
        int pageSize = 8;
        int startIndex = (CurrentPage - 1) * pageSize;
        if (type == 0) {
            type = null;
        }
        List<News> news = newsDao.selectNews(startIndex,pageSize,type,title);
        int row = newsDao.getNewsCount();
        int newsPage = row / pageSize;
        if(row % pageSize != 0){
            newsPage++;
        }
        PageObject<News> poNew = new PageObject<>();
        poNew.setPageCount(newsPage);
        poNew.setPageCurrent(CurrentPage);
        poNew.setRecords(news);
        poNew.setRowCount(row);
        return poNew;
    }

    @Override
    public FileHelper deleteNews(String[] id) {
        FileHelper helper = new FileHelper();

        //遍历id
        for (String index : id) {
            int state = newsDao.deleteNews(index);
            if (state == 1) {
                helper.add(index);
            }
        }
        return helper;
    }

    @Override
    public News selectNewsById(int id) {
        return newsDao.selectNewsById(id);
    }

    @Override
    public int getNewsCount() {
        return newsDao.getNewsCount();
    }

    @Override
    public List<News> selectNewsInCount(Integer type, Integer count, Integer flag) {
        return newsDao.selectNewsInCount(type, count, flag);
    }

    @Override
    public int updateNewsFlag(Integer id) {
        return newsDao.updateNewsFlag(id);
    }

    @Override
    public List<News> selectNewsByFuzzyName(News news) {
        return newsDao.selectNewsListByFuzzyName(news.getTitle());
    }

    @Override
    public int getRecommendedNewsCount() {
        return newsDao.getRecommendedNewsCount();
    }
}
