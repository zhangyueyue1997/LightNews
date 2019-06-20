package com.pb.dao;

import com.pb.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {
    /**添加新闻*/
    int insertNews(News news);
    /**在后台新闻维护中显示信息*/
    List<News> selectNews(@Param("startIndex") int startIndex,
                          @Param("pageSize") int pageSize,
                          @Param("type") Integer type,
                          @Param("title") String title);
    /**查询条数*/
    int getNewsCount();
    /**删除新闻*/
    int deleteNews(@Param("id") String id);
    /**根据id查询新闻详情*/
    News selectNewsById(@Param("id") int id);

    /***
     * 根据新闻类型/标记为首页推荐新闻查询指定条数的结果集
     * @param type 新闻类型
     * @param count 新闻条数
     * @param flag 是否是推荐新闻 1: 是 0: 否
     * @return 新闻结果集
     */
    List<News> selectNewsInCount(@Param("type") Integer type,
                                 @Param("count") Integer count,
                                 @Param("flag") Integer flag);

    /***
     * 设置是否为推荐新闻
     * @param id 待设置的新闻id
     * @return 受影响行数
     */
    int updateNewsFlag(@Param("id") Integer id);
}
