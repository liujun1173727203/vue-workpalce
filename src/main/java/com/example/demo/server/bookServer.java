package com.example.demo.server;

import java.util.List;

import com.example.demo.entity.Books;

public interface bookServer {
	List<Books> getlist();
	Books findBookname(String bookname);
	Books findBookid(String id);
	Boolean addBook(Books book);
	Boolean upStock(Books book);
	Boolean delBook(String id);
}
