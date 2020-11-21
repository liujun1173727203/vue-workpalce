package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.server.userServer;

@RestController
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private userServer userserver;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> login(@RequestParam(value="readerid") String username,@RequestParam(value="password") String password) {
		Map<String,Object> map =new HashMap<String,Object>();
		if(userserver.login(username, password)) {
			Users user=userserver.findUsername(username);
			map.put("msg", "登陆成功");
			map.put("username",user.getUsername());
			if("是".equals(user.getIsAdmin())) {
			map.put("isAdmin",true);
			}else {
			map.put("isAdmin",false);
			}
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("msg","用户名或密码错误");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@RequestMapping(value="/get_user_list",method=RequestMethod.GET)
	public List<Users> getUserList(){
		return userserver.getUsers();
	}
	
	@RequestMapping(value="/deluser/{id}",method=RequestMethod.GET)
	public Boolean delUser(@PathVariable int id) {
		return userserver.delUser(id);
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public Boolean addUser(Users user) {
		if (user.getIsAdmin()==null) {
			user.setIsAdmin("否");
		}	
		return userserver.addUser(user);
	}
}
