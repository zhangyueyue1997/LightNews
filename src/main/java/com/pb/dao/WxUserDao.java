package com.pb.dao;

import com.pb.pojo.WxUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * 对应mapper文件的持久（dao）层数据
 * @author zgl
 *
 */
public interface WxUserDao {
    /**（可以根据用户名）查找页面信息*/
    List<WxUser> findPageObjects(
            @Param("nickName") String openid,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    /**
     * 根据推荐人查找分页数据
     * @param referrer 用户的openid
     * @param startIndex 起始页
     * @param pageSize 每页大小
     * @return 微信用户列表
     */
    List<WxUser> findPageObjectsByRefer(
            @Param("referrer") String referrer,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    /**获取总记录数*/
    int getRowCount();

    /***
     * 根据Openid查找总记录数
     * @param openid 微信用户唯一指定ID
     * @return 总记录数
     */
    int getRowCountByOpenid(@Param("openid") String openid);

    /**根据选择的id删除数据*/
    int deleteObjects(@Param("ids")String[] ids);
    /**添加数据*/
    int saveObjects(WxUser entity);
    /**更新数据信息*/
    int updateObjects(WxUser entity);
    /**根据id查找数据*/
    WxUser findObjectById(@Param("openid")String id);
    /**根据openid删除数据*/
    int updateStatusByOpenid(@Param("openid") String openid);
    /**查询用户积分*/
    int updateIntegral(WxUser wxUser);
    /**查询用户积分*/
    WxUser selectUserIntegral(@Param("oppen_id") String open_id);
    /**根据openId查询二维码图片 */
    String findQRCodeByOpenId(@Param("openId") String openId);

    /***
     * 增加推荐人积分
     * @param entity 参数Bean
     * integral: 要增加的积分数
     * openid: 推荐人
     * @return 受影响行数
     */
    int addIntegralToReferrer(WxUser entity);
}
