package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Users {
	@ApiModelProperty(value="用户id")
	private int readerId;
	@ApiModelProperty(value="用户名")
	private String username;
	@ApiModelProperty(value="账号")
	private String account;
	private int max_num;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@ApiModelProperty(value="用户密码")
	private String password;
	@ApiModelProperty(value="用户是否拥有管理员权限")
	private String isAdmin;
	@ApiModelProperty(value="用户性别")
	private String sex;
	@ApiModelProperty(value="用户身份")
	private String usertype;
	@ApiModelProperty(value="用户借阅量")
	private int maxNum;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
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
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getMax_num() {
		return max_num;
	}
	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}
}
