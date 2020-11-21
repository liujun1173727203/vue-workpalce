package com.example.demo.server;

import java.util.List;

import com.example.demo.entity.Borrow;

public interface borrowServer {
	List<Borrow> getList();
	Boolean upBorrow(Borrow borrow);
	Boolean addBorrow(Borrow borrow);
}
