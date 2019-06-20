package com.pb.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

import java.io.Serializable;
import java.sql.Date;

/***
 * 实体类用于封装Data实例到数据库中
 * @author zgl
 */
public class Data implements Serializable {
    private static final long serialVersionUID = 7888767860272685092L;

    private int id;
    private String content;
    private int type;
    private Date createdTime;
    private String createdUser;
    private String modifiedUser;
    private Date modifiedTime;
    private String address;
    private String baiduyun;
    private int status;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBaiduyun() {
        return baiduyun;
    }

    public void setBaiduyun(String baiduyun) {
        this.baiduyun = baiduyun;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
