package com.pb.pojo;

import java.io.Serializable;

/***
 * 封装用户和与之对应的公司Pojo类
 *
 * @author zgl
 */
public class UCompany implements Serializable {
    private static final long serialVersionUID = -8829948565808702095L;
    private WxUser user;
    private Company company;


    public WxUser getUser() {
        return user;
    }

    public void setUser(WxUser user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
