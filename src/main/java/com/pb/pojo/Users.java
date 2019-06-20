package com.pb.pojo;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

/***
 * 实体类用于封装数据到数据库中
 * @author Yang
 *
 */
public class Users implements Serializable{
	
	private static final long serialVersionUID = -5514805296845574106L;

	private Integer id;
	private String username;
	private String password;
	private Date createdTime;
	private String createdUser;
	private String modifiedUser;
	private Date modifiedTime;
	private String role;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getMoodifiedTime() {
		return modifiedTime;
	}
	public void setMoodifiedTime(Date moodifiedTime) {
		this.modifiedTime = moodifiedTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", createdTime=" + createdTime +
				", createdUser='" + createdUser + '\'' +
				", modifiedUser='" + modifiedUser + '\'' +
				", modifiedTime=" + modifiedTime +
				", role='" + role + '\'' +
				'}';
	}
}
