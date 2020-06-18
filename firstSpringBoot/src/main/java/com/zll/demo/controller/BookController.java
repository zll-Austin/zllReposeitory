package com.zll.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.zll.demo.aop.service.BookService;
import com.zll.demo.component.Book;
import com.zll.demo.component.GlobalAuthor;
import com.zll.demo.component.GlobalDataBook;

//@RestController /* @Controller + @ResponseBody*/
@Controller
public class BookController {
	@Autowired
	Book book;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@GetMapping("/book")
	@ResponseBody
	public String book(){
		return book.toString();
	}
	
	@GetMapping("/books")
	@ResponseBody
	public ModelAndView books(){
		List<Book> books = new ArrayList<>();
		Book b1 = new Book();
		b1.setId(1);
		b1.setAuthor("你爸爸还是你爸爸");
		b1.setName("爸爸去哪了");
		b1.setPrice(28.6);
		b1.setPublicationDate(new Date());
		Book b2 = new Book();
		b2.setId(2);
		b2.setAuthor("你大爷还是你大爷");
		b2.setName("你大爷的");
		b2.setPrice(66.6);
		b2.setPublicationDate(new Date());
		books.add(b1);
		books.add(b2);
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", books);
		mv.setViewName("books");
		return mv;
	}
	
	@GetMapping("/jsonbook")
	@ResponseBody
	public Book jsonbook(){
		Book book1 = new Book();
		book1.setId(1);
		book1.setAuthor("你爸爸还是你爸爸");
		book1.setName("爸爸去哪了");
		book1.setPrice(28.6);
		book1.setPublicationDate(new Date());
		return book1;
	}
	
	/**
	 * http://localhost:8080/globalBook?b.name=%E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89&b.author=%E7%BD%97%E8%B4%AF%E4%B8%AD&a.name=%E6%9B%B9%E9%9B%AA%E8%8A%B9&a.age=48
	 * @param book
	 * @param author
	 */
	@GetMapping("/globalBook")
	public void book(@ModelAttribute("b") GlobalDataBook book, @ModelAttribute("a") GlobalAuthor author) {
		System.out.println(book.toString() + ">>>>>>>>>>>>>" + author.toString());
	}
	
	@GetMapping("/bookOps")
	@ResponseBody
	public void bookOps(){
		Book book1 = new Book();
		book1.setId(1);
		book1.setAuthor("你爸爸还是你爸爸");
		book1.setName("爸爸去哪了");
		book1.setPrice(28.6);
		book1.setPublicationDate(new Date());
		int i = bookService.addBook(book1);
		System.out.println("addBook  >>>> "+i);
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setAuthor("你大爷还是你大爷");
		book2.setName("大爷牛逼");
		book2.setPrice(66.6);
		book2.setPublicationDate(new Date());
		int updateBook=bookService.updateBookById(book2);
		System.out.println("updateBook  >>>> "+updateBook);
		
		Book book3 = bookService.getBookById(1);
		System.out.println("getBookById  >>>> "+book3);
		
		int delete = bookService.deleteBookById(2);
		System.out.println("deleteBookById  >>>> "+delete);
		
 		List<Book> allBook = bookService.getAllBooks();
		System.out.println("getAllBooks  >>>> "+allBook);
	}
	
	@GetMapping("/test1")
	@ResponseBody
	public void test1() {
		ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
		ops1.set("name", "三国演义");
		String name = ops1.get("name");
		ValueOperations ops2 = redisTemplate.opsForValue();
		Book b1 = new Book();
		b1.setId(1);
		b1.setAuthor("曹雪芹");
		b1.setName("红楼梦");
		ops2.set("b1", b1);
		Book book = (Book) ops2.get("b1");
		System.out.println(book);
	}
}
