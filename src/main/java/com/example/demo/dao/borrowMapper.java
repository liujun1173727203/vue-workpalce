package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.entity.Borrow;

@Mapper
public interface borrowMapper {
	@Results(id="borrow",value= {
			@Result(column="id",property="id",jdbcType=JdbcType.VARCHAR),
			@Result(column="book_id",property="bookId",jdbcType=JdbcType.VARCHAR),
			@Result(column="reader_id",property="readerId",jdbcType=JdbcType.VARCHAR),
			@Result(column="if_back",property="isBack",jdbcType=JdbcType.VARCHAR),
			@Result(column="borrow_date",property="borrowDate",jdbcType=JdbcType.DATE),
			@Result(column="back_date",property="backDate",jdbcType=JdbcType.DATE),
	})
	@Select("select * from borrow")
	List<Borrow> getlist();
	
	@Update("update borrow set back_date=#{backDate},if_ack=#{isBack} where id=#{id}")
	Boolean upBorrow(Borrow borrow);
	
	@Insert("insert into borrow(book_id,reader_id,if_back,borrow_date)value(#{bookId},#{readerId},#{isBack},#{borrowDate}")
	Boolean addBorrow(Borrow borrow);
	
}
