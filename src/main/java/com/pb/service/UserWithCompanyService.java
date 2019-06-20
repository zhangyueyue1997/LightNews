package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.UCompany;

public interface UserWithCompanyService {
    /***
     * 根据推荐人查找
     * @param pageCurrent
     * @param referrer
     * @return
     */
    PageObject<UCompany> findObjectsByPage(Integer pageCurrent,String referrer);

    /***
     * 根据openid查找企业所有人及其企业信息
     * @param openid 所有人Openid
     * @return 结果集
     */
    UCompany findObjectByOpenid(String openid);
}
