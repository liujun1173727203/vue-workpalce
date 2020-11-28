package com.example.demo.server.serverlmpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.bookMapper;
import com.example.demo.dao.borrowMapper;
import com.example.demo.dao.userMapper;
import com.example.demo.entity.Books;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.BorrowInfo;
import com.example.demo.entity.Users;
import com.example.demo.server.borrowServer;

@Service
public class borrowServerlmpl implements borrowServer{

	@Autowired
	private borrowMapper borrowmapper;
	@Autowired
	private userMapper usermapper;
	@Autowired
	private bookMapper bookmapper;
	
	@Override
	public List<BorrowInfo> getList() {
		List<BorrowInfo> lists =new ArrayList<BorrowInfo>();
		for(Borrow i : borrowmapper.getlist()){
			System.out.println(i.getId());
			Users user =usermapper.findById(i.getReaderId());
			Books book =bookmapper.findBookid(i.getBookId().substring(1));
			BorrowInfo info = new BorrowInfo();
			info.setAccount(user.getAccount());
			info.setAuthor(book.getAuthor());
			info.setBackDate(i.getBackDate());
			info.setBookname(book.getBookname());
			info.setBooktype(book.getBooktype());
			info.setBorrowDate(i.getBorrowDate());
			info.setId(i.getId());
			info.setIsBack(i.getIsBack());
			info.setPublisher(book.getPublisher());
			info.setUsername(user.getUsername());
			lists.add(info);
		}
		return lists;
	}

	@Override
	public Boolean upBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		return borrowmapper.upBorrow(borrow);
	}

	@Override
	public Boolean addBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		return borrowmapper.addBorrow(borrow);
	}

}
