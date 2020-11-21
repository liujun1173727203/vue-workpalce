package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Books;
import com.example.demo.server.bookServer;

@RestController
@RequestMapping("/book")
public class bookController {
	@Autowired
	private bookServer bookserver;
	
	@RequestMapping(value="/getlist",method=RequestMethod.GET)
	public List<Books> getList(){
		return bookserver.getlist();
	}
	
	@RequestMapping(value="/find_name/{bookname}",method=RequestMethod.GET)
	public Books findBookName(@PathVariable String bookname) {
		return bookserver.findBookname(bookname);
	}
	@RequestMapping(value="/find_id/{id}",method=RequestMethod.GET)
	public Books findBookId(@PathVariable String id) {
		return bookserver.findBookid(id);
	}
	
	@RequestMapping(value="/up_stock",method=RequestMethod.POST)
	public Boolean upStock(Books books) {
		return bookserver.upStock(books);
	}
	@RequestMapping(value="/delbook",method=RequestMethod.DELETE)
	public Boolean delBook(String id) {
		return bookserver.delBook(id);
	}
	@RequestMapping(value="/add_book",method=RequestMethod.POST)
	public Boolean addBook(Books books) {
		return bookserver.addBook(books);
	}
}
