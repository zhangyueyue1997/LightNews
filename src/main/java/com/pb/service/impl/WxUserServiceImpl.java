package com.pb.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.pb.common.Utils.WechatHelper;
import com.pb.common.vo.PageObject;
import com.pb.dao.IntegralSettingDao;
import com.pb.dao.WxUserDao;
import com.pb.pojo.WxUser;
import com.pb.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 业务层的具体实现类
 * @author zgl
 *
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao = null;
    @Autowired
    private IntegralSettingDao integralDao;

    @Override
    public PageObject<WxUser> findPageObjects(String openid,Integer pageCurrent) {
        int pageSize = 8;//设置单页显示的数据条目数为3.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<WxUser> records = wxUserDao.findPageObjects(openid,startIndex, pageSize);//获取数据
        int rowCount = wxUserDao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<WxUser> obj = new PageObject<>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)

    }

    @Override
    public int deleteObjects(String[] ids) {
        return wxUserDao.deleteObjects(ids);
    }

    @Override
    public int saveObjects(WxUser entity) {
        return wxUserDao.saveObjects(entity);
    }

    @Override
    public int updateObjects(WxUser entity) {
        return wxUserDao.updateObjects(entity);
    }

    @Override
    public WxUser findObjectById(String id) {
        return wxUserDao.findObjectById(id);
    }



    /***
     * 获取微信用户个人基本信息
     * @param code 当用户登录公众号时,微信服务器发送的代码,用于再次与服务器交互获取Access_Token
     *             仅一次有效,且不能被两次请求共同使用
     * @param appId 微信公众号应用ID
     * @param secret 微信公众号应用密令
     * @param referrer 推荐人OpenId
     * @return 个人信息结果Bean
     */
    @Override
    public WxUser getWxUserInfo(String code, String appId, String secret,String referrer) {
        Map<String,String> resultMap = new HashMap<>();
        try {
            //使用微信工具类从微信服务器中拿到用户个人信息
            resultMap = WechatHelper.getWechatUserInfo(code, appId, secret);
        } catch (Exception e) {
            //与微信服务器交互出现错误
            e.printStackTrace();
        }

        if (resultMap != null) {
            //获得openid
            String openId = resultMap.get("openId");
            //查询数据库,查询用户是否是第一次登录
            WxUser user = wxUserDao.findObjectById(openId);
            //用户是第一次登录,将数据库存到数据库中
            if (user == null) {
                //设置初始账号信息
                user = new WxUser();
                user.setOpenid(openId);
                user.setCreatedUser("管理员");
                //设置默认积分
                user.setIntegral(integralDao.selDefaultIntegral());

                if (referrer.equals("null")) {
                    referrer = "暂无";
                }

                user.setReferrer(referrer);
                //设置受奖励推荐人Bean
                WxUser awardedUser = new WxUser();
                //查询推荐人推荐一个人获得多少个积分
                awardedUser.setIntegral(integralDao.selReferIntegral());
                //设置推荐人的openid
                awardedUser.setOpenid(referrer);
                //更新推荐人积分
                wxUserDao.addIntegralToReferrer(awardedUser);
                // 生成指定url对应的二维码到文件，宽和高都是300像素
                String qrCode = "http://www.tjshy.net/wxInformation/index.do?referrer=" + openId;
                String qrCodePath = "/zl/QRCode/" + IdUtil.fastSimpleUUID() + ".jpg";
                QrCodeUtil.generate(qrCode, 300, 300, FileUtil.file(qrCodePath));
                user.setQrCode(qrCodePath);
                //用户不是第一次登录,将从微信服务器传来的用户个人信息写入结果Bean
                user.setNickName(resultMap.get("nickName"));
                user.setHeadImg(resultMap.get("headImg"));
                user.setCityName(resultMap.get("cityName"));
                user.setProvinceName(resultMap.get("provinceName"));
                user.setCountry(resultMap.get("country"));
                user.setSex(resultMap.get("sex"));
                System.out.println(user);
                //保存数据库实例
                wxUserDao.saveObjects(user);
            }
            return user;
        }
        //查询失败
        return null;
    }

    @Override
    public int updateStatusByOpenid(String openid) {
        return wxUserDao.updateStatusByOpenid(openid);
    }

    @Override
    public int updateIntegral(WxUser wxUser) {
        return wxUserDao.updateIntegral(wxUser);
    }

    @Override
    public WxUser selectUserIntegral(String oppen_id) {
        return wxUserDao.selectUserIntegral(oppen_id);
    }

    @Override
    public String findQRCodeByOpenId(String openId) {
        return wxUserDao.findQRCodeByOpenId(openId);
    }

}
