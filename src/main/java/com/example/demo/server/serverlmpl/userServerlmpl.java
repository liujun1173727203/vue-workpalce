package com.example.demo.server.serverlmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.userMapper;
import com.example.demo.entity.Users;
import com.example.demo.server.userServer;

@CacheConfig(cacheNames = "emp")
@Service
public class userServerlmpl implements userServer{

	@Autowired
	private userMapper usermapper;
	@Override
	public List<Users> getUsers() {
		List<Users> users =usermapper.getUsers();
		for (Users user : users){
			user.setPassword("*******");
		}
		return users;
	}

	@Override
	public Users findUser(String isAdmin) {
		Users user=usermapper.findUser(isAdmin);
		user.setPassword("******");
		return user;
	}

	@Override
	public Boolean addUser(Users user) {
		// TODO Auto-generated method stub
		return usermapper.addUser(user);
	}

	@Override
	public Boolean delUser(int readerid) {
		// TODO Auto-generated method stub
		return usermapper.delUser(readerid);
	}

	@Override
	@Cacheable(cacheNames={"isLogin"})
	public Boolean login(String readerid,String password) {
		if(password.equals(usermapper.login(readerid).getPassword())) {
			return true;
		};
		return false;
	}

	@Override
	public List<Users> findUsername(String username) {
		List<Users> users=usermapper.findUsers('%'+username+'%');
		for (Users user : users) { 
		user.setPassword("*****");
		}
		return users;
	}

	@Override
	public Users findByAccount(String account) {
		return usermapper.login(account);
	}

}
