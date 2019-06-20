package com.pb.pojo;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pb.common.DataJsonSerializer;

/**
 * Created by ly on 2019/3/22.
 */
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 3473138458954285452L;
	private int id;
	private String name;
	private String content;
	private Date createdTime;
	private String createdUser;
	private String modifiedUser;
	private Date modifiedTime;
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}
