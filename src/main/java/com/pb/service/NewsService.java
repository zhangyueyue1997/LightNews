package com.pb.service;

import com.pb.common.Utils.FileHelper;
import com.pb.common.vo.PageObject;
import com.pb.pojo.News;
import org.apache.ibatis.annotations.Param;

import javax.lang.model.element.NestingKind;
import java.util.List;

public interface NewsService {
    /**添加新闻*/
    int insertNews(News news);
    /**在后台新闻维护中显示信息*/
    PageObject<News> selectNews(Integer CurrentPage,Integer type,String title);
    /**删除新闻*/
    FileHelper deleteNews(String[] id);
    /**前台显示新闻详情*/
    News selectNewsById(int id);
    /**查询新闻条数*/
    int getNewsCount();
    List<News> selectNewsInCount(Integer type,Integer count,Integer flag);
    int updateNewsFlag(Integer id);
    List<News> selectNewsByFuzzyName(News news);
    int getRecommendedNewsCount();
}
