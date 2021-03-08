package com.xiaoer.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoer.demo.entity.Counts;
import com.xiaoer.demo.entity.Users;
import com.xiaoer.demo.mapper.CountMapper;
import com.xiaoer.demo.mapper.UsersMapper;

@RestController
public class UserController {
	@Autowired
	private UsersMapper usermapper;
	
	@Autowired
	private CountMapper countmapper;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> login(String username,String password) {
		Map<String,Object> map =new HashMap<String,Object>();
		if("".equals(username)||username==null||password==null||"".equals(password)) {
			map.put("msg","用户名或密码为空");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
		try {
			Users user =usermapper.login(username);
			if(password.equals(user.getPassword())) {
				user.setPassword("******");
				map.put("msg",user);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		};
		}catch(NullPointerException e) {
			map.put("msg", "用户不存在");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
		map.put("msg","用户名或密码错误");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/adduser")
	public ResponseEntity<Map<String,Object>> addUser(String username,String password){
		System.out.println(username);
		System.out.println(password);
		Map<String,Object> map =new HashMap<String,Object>();
		if(usermapper.login(username) != null) {
			map.put("msg","用户已存在");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
		usermapper.addUser(username,password);
		map.put("msg","注册成功");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@GetMapping("/getcount")
	public List<Counts> getCounts(String userid){
		return countmapper.getCounts(userid);
	}
	@PostMapping("/addcount")
	public Boolean addCount(Counts count) {
		return countmapper.addCount(count);
	}
	
}
