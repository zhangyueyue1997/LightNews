package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.WxUser;
import org.apache.ibatis.annotations.Param;

/***
 * 业务层接口用于对接控制层（Controller）和持久层（dao）
 * @author zgl
 *
 */
public interface WxUserService {
    /**查找页面信息*/
    PageObject<WxUser> findPageObjects(String openid,Integer pageCurrent);
    /**根据id值删除对象*/
    int deleteObjects(String[] ids);
    /**保存信息*/
    int saveObjects(WxUser entity);
    /**更新信息*/
    int updateObjects(WxUser entity);
    /**根据id查找数据*/
    WxUser findObjectById(String id);
    /**获得微信用户消息*/
    WxUser getWxUserInfo(String code, String appId,String secret,String referrer);
    /**根据openid删除用户*/
    int updateStatusByOpenid(String openid);
    /**扣除用户积分*/
    int updateIntegral(WxUser wxUser);
    /**查询用户积分*/
    WxUser selectUserIntegral(String oppen_id);
    /**根据openId查询二维码图片 */
    String findQRCodeByOpenId(@Param("openId") String openId);
}
