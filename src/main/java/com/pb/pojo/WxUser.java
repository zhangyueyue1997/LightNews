package com.pb.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

import java.io.Serializable;
import java.sql.Date;

/***
 * 实体类用于封装WxUser实例到数据库中
 * @author zgl
 */
public class WxUser implements Serializable {
    private static final long serialVersionUID = 590110081060088829L;

    private String openid;
    private String nickName;
    private String headImg;
    private String sex;
    private String cityName;
    private String country;
    private String provinceName;
    private double integral;
    private Date createdTime;
    private String createdUser;
    private String modifiedUser;
    private Date modifiedTime;
    private String referrer;
    private String name;
    private String introduce;
    private int id;
    private String QRCode;


    public String getQrCode() {
        return QRCode;
    }

    public void setQrCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public int getid() {
        return id;
    }

    public void setid(int listid) {
        this.id = listid;
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


    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }


    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }



    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
        return "WxUser{" +
                "openid='" + openid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", sex='" + sex + '\'' +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", integral=" + integral +
                ", createdTime=" + createdTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                ", referrer='" + referrer + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", id=" + id +
                ", qrCode='" + QRCode + '\'' +
                '}';
    }
}
