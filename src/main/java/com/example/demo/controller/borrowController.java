package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.BorrowInfo;
import com.example.demo.server.borrowServer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags="借阅记录")
@RequestMapping(value="/borrow")
public class borrowController {
	
	@Autowired
	private borrowServer borrowserver;
	@ApiOperation("获取借阅记录列表")
	@RequestMapping(value="/getlist",method=RequestMethod.GET)
	public List<BorrowInfo> getList(){
		return borrowserver.getList();
	}
	@ApiOperation("更新借阅记录列表")	
	@RequestMapping(value="/upborrow",method=RequestMethod.PUT)
	public Boolean upBorrow(Borrow borrow) {
		return borrowserver.upBorrow(borrow);
	}
	@ApiOperation("添加借阅记录列表")
	@RequestMapping(value="/addborrow",method=RequestMethod.POST)
	public Boolean addBorrow(Borrow borrow) {
		return borrowserver.addBorrow(borrow);
	}
}
