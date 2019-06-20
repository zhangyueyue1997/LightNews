package com.pb.dao;

import com.pb.pojo.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/***
 * 对应mapper文件的持久（dao）层数据
 * @author zgl
 *
 */
public interface DataDao {
    /**（可以根据用户名）查找页面信息*/
    List<Data> findPageObjects(
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
    /**获取总记录数*/
    int getRowCount();
    /**根据选择的id删除数据*/
    int deleteObjects(@Param("ids")String[] ids);
    /**添加数据*/
    int saveObjects(Data entity);
    /**更新数据信息*/
    int updateObjects(Data entity);
    /**根据id查找数据*/
    Data findObjectById(@Param("id")Integer id);
    /**根据content更新状态*/
    int updateStatusByNameOrID(@Param("content") String content,@Param("id")String id);
    /**根据文件名,拓展名查找文件*/
    List<Data> findByNameAndType(@Param("content")String content,
                                 @Param("type")Integer type,
                                 @Param("startIndex") Integer startIndex,
                                 @Param("pageSize") Integer pageSize);
}
