package com.pb.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    private static final long serialVersionUID = -5605843517735032993L;
    private String title;
    private String content;
    private int status;
    private Date createdTime;
    private String createdUser;
    private Date modifiedTime;
    private String modifiedUser;
    private int id;
    private int type;
    private int flag;
    private String thumbNail;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    @JsonSerialize(using = DataJsonSerializer.class)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", id=" + id +
                ", type=" + type +
                ", thumbNail='" + thumbNail + '\'' +
                '}';
    }
}
