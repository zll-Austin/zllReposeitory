package com.zll.demo.aop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zll.demo.aop.mapper.BookMapper;
import com.zll.demo.component.Book;

@Service
public class BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	public int addBook(Book book) {
		return bookMapper.addBook(book);
	}
	
	public int updateBookById(Book book) {
		return bookMapper.updateBookById(book);
	}
	
	public int deleteBookById(Integer id) {
		return bookMapper.deleteBookById(id);
	}
	
	public Book getBookById(Integer id) {
		return bookMapper.getBookById(id);
	}
	public List<Book> getAllBooks() {
		return bookMapper.getAllBooks();
	}
}
