package com.pb.dao;

import com.pb.pojo.Publish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * 对应mapper文件的持久（dao）层数据
 * @author zgl
 *
 */
public interface PublishDao {
    /**查找页面信息*/
    List<Publish> findPageObjects(
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize,
            @Param("type")Integer type);
    /**获取总记录数*/
    int getRowCount();
    /**根据选择的id删除数据*/
    int deleteObjects(@Param("ids")String[] ids);
    /**添加数据*/
    int saveObjects(Publish entity);
    /**更新数据信息*/
    int updateObjects(Publish entity);
    /**根据id查找数据*/
    Publish findObjectById(@Param("id")Integer id);
    /**index页查询商机信息记录总数*/
    int selectCountByType(Publish publish);
    /**审批发布的信息*/
    int updateStatus(Publish publish);
    /**微信页面显示审核通过的信息*/
    List<Publish> selectPageObjectsByStatus(@Param("startIndex") Integer startIndex,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("type")Integer type);

    /**查询是否已经审批*/
    int selectStatusById(@Param("id") int id);
}
