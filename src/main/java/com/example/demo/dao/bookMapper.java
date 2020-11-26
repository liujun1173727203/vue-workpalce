package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.entity.Books;

@Mapper
public interface bookMapper {
	@Results(id="book",value= {
			@Result(column="id",property="id",jdbcType=JdbcType.CHAR),
			@Result(column="bookname",property="bookname",jdbcType=JdbcType.VARCHAR),
			@Result(column="booktype",property="booktype",jdbcType=JdbcType.CHAR),
			@Result(column="author",property="author",jdbcType=JdbcType.CHAR),
			@Result(column="publisher",property="publisher",jdbcType=JdbcType.VARCHAR),
			@Result(column="stock",property="stock",jdbcType=JdbcType.INTEGER),
	})
	@Select("select * from book")
	List<Books> getlist();
	
	@Select("select * from book where bookname=#{bookname}")
	@ResultMap(value="book")
	Books findBookname(String bookname);
	
	@Select("select * from book where id=#{id}")
	@ResultMap(value="book")
	Books findBookid(String id);
	
	@Insert("insert into book(id,bookname,booktype,author,publisher,stock)value(#{id},#{bookname},#{booktype},#{author},#{publisher},#{stock})")
	Boolean addBook(Books book);
	
	@Update("update book set stock=#{stock} where id=#{id}")
	Boolean upStock(Books book);
	
	@Delete("delete from book where id=#{id}")
	Boolean delBook(String id);
}
