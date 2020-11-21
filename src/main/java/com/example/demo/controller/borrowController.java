package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Borrow;
import com.example.demo.server.borrowServer;

@RestController
@RequestMapping(value="/borrow")
public class borrowController {
	
	@Autowired
	private borrowServer borrowserver;
	
	@RequestMapping(value="/getlist",method=RequestMethod.GET)
	public List<Borrow> getList(){
		return borrowserver.getList();
	}
	
	@RequestMapping(value="/upborrow",method=RequestMethod.PUT)
	public Boolean upBorrow(Borrow borrow) {
		return borrowserver.upBorrow(borrow);
	}
	
	@RequestMapping(value="/addborrow",method=RequestMethod.POST)
	public Boolean addBorrow(Borrow borrow) {
		return borrowserver.addBorrow(borrow);
	}
}
