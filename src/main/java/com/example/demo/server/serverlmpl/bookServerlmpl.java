package com.example.demo.server.serverlmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.bookMapper;
import com.example.demo.entity.Books;
import com.example.demo.server.bookServer;

@Service
public class bookServerlmpl implements bookServer{

	@Autowired
	private bookMapper bookmapper;
	@Override
	public List<Books> getlist() {
		// TODO Auto-generated method stub
		return bookmapper.getlist();
	}

	@Override
	public List<Books> findBookname(String bookname) {
		// TODO Auto-generated method st
		System.out.println('%'+bookname+'%');
		return bookmapper.findBookname('%'+bookname+'%');
	}

	@Override
	public Books findBookid(String id) {
		// TODO Auto-generated method stub
		return bookmapper.findBookid(id);
	}

	@Override
	public Boolean addBook(Books book) {
		// TODO Auto-generated method stub
		return bookmapper.addBook(book);
	}

	@Override
	public Boolean upStock(Books book) {
		// TODO Auto-generated method stub
		return bookmapper.upStock(book);
	}

	@Override
	public Boolean delBook(String id) {
		// TODO Auto-generated method stub
		return bookmapper.delBook(id);
	}

}
