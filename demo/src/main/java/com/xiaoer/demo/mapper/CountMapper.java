package com.xiaoer.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xiaoer.demo.entity.Counts;

@Mapper
public interface CountMapper {
	@Results(id ="count",value= {
			@Result(column="id",property="id",jdbcType = JdbcType.INTEGER),
			@Result(column="username",property="username",jdbcType = JdbcType.VARCHAR),
			@Result(column="password",property="password",jdbcType = JdbcType.VARCHAR),
	})
	@Select("select * from count where userid=#{userid}")
	List<Counts> getCounts(String userid);
	
	@Insert("insert into count(userid,chapter,count)value(#{userid},#{chapter},#{count})")
	Boolean addCount(Counts count);
}
