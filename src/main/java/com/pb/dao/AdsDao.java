package com.pb.dao;

import com.pb.pojo.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * 对应mapper文件的持久（dao）层数据
 * @author zgl
 *
 */
public interface AdsDao {
    /**（可以根据用户名）查找页面信息*/
    List<Advertisement> findPageObjects(
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
    /**获取总记录数*/
    int getRowCount();
    /**根据选择的id删除数据*/
    int deleteObjects(@Param("ids")String[] ids);
    /**添加数据*/
    int saveObjects(Advertisement entity);
    /**更新数据信息*/
    int updateObjects(Advertisement entity);
    /**根据id查找数据*/
    Advertisement findObjectById(@Param("id")Integer id);
    /**删除广告*/
    int deleteAdsById(Advertisement advertisement);
}
