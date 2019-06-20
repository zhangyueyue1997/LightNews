package com.pb.common.vo;

public class DataListInform {
	
	private int id;
	private String content;
	private String createdUser;
	private int type;
	private String address;
	private String baiduyun;

	public String getBaiduyun() {
		return baiduyun;
	}

	public void setBaiduyun(String baiduyun) {
		this.baiduyun = baiduyun;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DataListInform [id=" + id + ", content=" + content +
				", createdUser=" + createdUser + ", type=" + type + ", address=" + address
				+", baiduyun = " + baiduyun+ "]";
	}


}
