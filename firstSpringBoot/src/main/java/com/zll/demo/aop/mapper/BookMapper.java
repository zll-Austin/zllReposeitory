package com.zll.demo.aop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zll.demo.component.Book;

@Mapper
public interface BookMapper {
	int addBook(Book book);
	int deleteBookById(Integer id);
	int updateBookById(Book book);
	Book getBookById(Integer id);
	List<Book> getAllBooks();
}
