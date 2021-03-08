package com.xiaoer.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xiaoer.demo.entity.Questions;
import com.xiaoer.demo.entity.utils;

@Mapper
public interface QuestionMapper {
	@Results(id ="question",value= {
			@Result(column="id",property="id",jdbcType = JdbcType.INTEGER),
			@Result(column="int",property="type",jdbcType = JdbcType.INTEGER),
			@Result(column="chapter",property="chapter",jdbcType = JdbcType.INTEGER),
			@Result(column="score",property="score",jdbcType = JdbcType.INTEGER),
			@Result(column="level",property="level",jdbcType = JdbcType.INTEGER),
			@Result(column="question",property="question",jdbcType = JdbcType.VARCHAR),
			@Result(column="chioce",property="choice",jdbcType = JdbcType.VARCHAR),
			@Result(column="answer",property="answer",jdbcType = JdbcType.VARCHAR),
	})
	@Select("select * from c_quest")
	List<Questions> getQuestion();
	
	@Select("select * from c_quest where chapter= #{chapter}")
	@ResultMap(value="question")
	List<Questions> getQuestion1(String chapter);
	
	@Select("select * from c_quest where level= #{level}")
	@ResultMap(value="question")
	List<Questions> getQuestion2(String level);
	
	@Select("select * from c_quest where chapter= #{chapter} and level=#{level}")
	@ResultMap(value="question")
	List<Questions> getQuestion3(String chapter,String level);
	
	@Results(id="utils",value= {
			@Result(column="chapter",property="chapter",jdbcType = JdbcType.INTEGER),
			@Result(column="level",property="level",jdbcType = JdbcType.INTEGER),
	})
	@Select("select Max(chapter) as chapter,Max(level)  as level from c_quest")
	utils getUtil();
}
