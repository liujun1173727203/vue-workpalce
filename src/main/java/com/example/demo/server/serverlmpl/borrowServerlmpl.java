package com.example.demo.server.serverlmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.borrowMapper;
import com.example.demo.entity.Borrow;
import com.example.demo.server.borrowServer;

@Service
public class borrowServerlmpl implements borrowServer{

	@Autowired
	private borrowMapper borrowmapper;
	@Override
	public List<Borrow> getList() {
		// TODO Auto-generated method stub
		return borrowmapper.getlist();
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
