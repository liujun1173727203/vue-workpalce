package com.example.demo.server;

import java.util.List;

import com.example.demo.entity.Users;

public interface userServer {
	Boolean login(String readerid,String password);
	List<Users> getUsers();
	List<Users> findUsername(String username);
	Users findUser(String isAdmin);
	Users findByAccount(String account);
	Boolean addUser(Users user);
	Boolean delUser(int readerid);
}
