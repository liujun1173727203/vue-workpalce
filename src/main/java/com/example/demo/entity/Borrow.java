package com.example.demo.entity;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Borrow {
	@ApiModelProperty(value="记录id")
	private String id;
	@ApiModelProperty(value="记录id")
	private String readerId;
	private String bookId;
	private Date borrowDate;
	private Date backDate;
	private String isBack;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
}
