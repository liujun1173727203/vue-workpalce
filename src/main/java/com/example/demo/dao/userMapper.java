package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.entity.Users;

@Mapper
public interface userMapper {
	@Select("select * from user")
	@Results(id="Users",value= {
			@Result(column="readerid",property="readerId",jdbcType=JdbcType.INTEGER),
			@Result(column="username",property="username",jdbcType=JdbcType.VARCHAR),
			@Result(column="password",property="password",jdbcType=JdbcType.VARCHAR),
			@Result(column="is_admin",property="isAdmin",jdbcType=JdbcType.VARCHAR),
			@Result(column="sex",property="sex",jdbcType=JdbcType.VARCHAR),
			@Result(column="usertype",property="usertype",jdbcType=JdbcType.VARCHAR),
			@Result(column="max_num",property="maxNum",jdbcType=JdbcType.INTEGER),
			@Result(column="account",property="account",jdbcType=JdbcType.VARCHAR),
	})
	List<Users> getUsers();
	
	@Select("select * from user where is_admin=#{isAdmin}")
	@ResultMap(value="Users")
	Users findUser(String isAdmin);

	@Select("select * from user where username like #{username}")
	@ResultMap(value="Users")
	List<Users> findUsers(String username);

	
	@Select("select * from user where account=#{account}")
	@ResultMap(value="Users")
	Users login(String account);
	
	@Select("select * from user where readerid=#{readerid}")
	@ResultMap(value="Users")
	Users findById(String readerid);
	
	@Insert("insert into user(username,password,is_admin,sex,usertype,max_num)value(#{username},#{password},#{isAdmin},#{sex},#{usertype},#{max_num})")
	Boolean addUser(Users user);
	
	@Delete("delete from user where readerid=#{readerid}")
	Boolean delUser(int readerid);
	
}
