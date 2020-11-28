package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Books;
import com.example.demo.server.bookServer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="书籍信息")
@RequestMapping("/book")
public class bookController {
	@Autowired
	private bookServer bookserver;
	@ApiOperation("获取书籍信息列表")
	@RequestMapping(value="/getlist",method=RequestMethod.GET)
	public List<Books> getList(){
		return bookserver.getlist();
	}
	@ApiOperation("根据书名查询书籍信息")	
	@RequestMapping(value="/find_name/{bookname}",method=RequestMethod.GET)
	public List<Books> findBookName(@PathVariable String bookname) {
		return bookserver.findBookname(bookname);
	}
	@ApiOperation("根据书籍id查询书籍信息")
	@RequestMapping(value="/find_id/{id}",method=RequestMethod.GET)
	public Books findBookId(@PathVariable String id) {
		return bookserver.findBookid(id);
	}
	@ApiOperation("更新书籍借阅状态")
	@RequestMapping(value="/up_stock",method=RequestMethod.POST)
	public Boolean upStock(Books books) {
		return bookserver.upStock(books);
	}
	@ApiOperation("删除书籍信息列表")
	@RequestMapping(value="/delbook",method=RequestMethod.DELETE)
	public Boolean delBook(String id) {
		return bookserver.delBook(id);
	}
	@CrossOrigin
	@ApiOperation("添加书籍信息列表")
	@RequestMapping(value="/add_book",method=RequestMethod.POST)
	public Boolean addBook(Books books) {
		return bookserver.addBook(books);
	}
}
