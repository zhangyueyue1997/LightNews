package com.pb.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

import java.io.Serializable;
import java.sql.Date;

/***
 * 实体类用于封装Company实例到数据库中
 * @author zgl
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 3473138458954285898L;
    private int id;

    public int getWxuserId() {
        return wxuserid;
    }

    public void setWxuserId(int wxuserid) {
        this.wxuserid = wxuserid;
    }

    private int wxuserid;
    private String openid;
    private String name;
    private String introduce;
    private Date createdTime;
    private String createdUser;
    private String modifiedUser;
    private Date modifiedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @JsonSerialize(using = DataJsonSerializer.class)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @JsonSerialize(using = DataJsonSerializer.class)
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", wxuserId=" + openid +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createdTime=" + createdTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
