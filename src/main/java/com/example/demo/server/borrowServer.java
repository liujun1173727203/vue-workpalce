package com.example.demo.server;

import java.util.List;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.BorrowInfo;

public interface borrowServer {
	List<BorrowInfo> getList();
	Boolean upBorrow(Borrow borrow);
	Boolean addBorrow(Borrow borrow);
}
