package com.xiaoer.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xiaoer.demo.entity.Users;

@Mapper
public interface UsersMapper {
	@Results(id ="users",value= {
			@Result(column="id",property="id",jdbcType = JdbcType.INTEGER),
			@Result(column="username",property="username",jdbcType = JdbcType.VARCHAR),
			@Result(column="password",property="password",jdbcType = JdbcType.VARCHAR),
	})
	@Select("select * from user where username=#{username}")
	Users login(String username);
	
	@Insert("insert into user(username,password)value(#{username},#{password})")
	Boolean addUser(String username,String password);
}
