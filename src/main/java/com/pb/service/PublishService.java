package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.Publish;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/***
 * 业务层接口用于对接控制层（Controller）和持久层（dao）
 * @author zgl
 *
 */
public interface PublishService {
    /**查找页面信息*/
    PageObject<Publish> findPageObjects(Integer pageCurrent,Integer type);
    /**根据id值删除对象*/
    int deleteObjects(String[] checkedIds);
    /**保存信息*/
    int saveObjects(Publish entity);
    /**更新信息*/
    int updateObjects(Publish entity);
    /**根据id查找数据*/
    Publish findObjectById(Integer id);

    /***
     * 上传图片,并返回图片URL
     * @param file 待上传路径
     * @return 图片URL
     */
    String uploadFile(MultipartFile file);
    /**index页查询商机信息记录总数*/
    int selectCountByType(Publish publish);
    /**审批发布的信息*/
    int updateStatus(Publish publish);
    /**微信页面显示审核通过的信息*/
    PageObject<Publish> selectPageObjectsByStatus(Integer pageCurrent,Integer type);
    /**查询是否已经审批*/
    int selectStatusById(int status);
}
