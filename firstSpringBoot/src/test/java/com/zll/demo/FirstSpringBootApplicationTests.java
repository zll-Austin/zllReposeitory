package com.zll.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.zll.demo.controller.BookDao;

@RunWith(SpringRunner.class)
@SpringBootTest
class FirstSpringBootApplicationTests {

	@Autowired
	BookDao bookDao;
	
	@Test
	void contextLoads() {
		/*
		 * bookDao.getBookById(100); String book = bookDao.getBookById(100);
		 * System.out.println(book); bookDao.updateBookById(100); String book2 =
		 * bookDao.updateBookById(100); System.out.println(book2);
		 * bookDao.deleteById(100); bookDao.getBookById(100); bookDao.getBookById2(99);
		 */
		
		String passwd = "123456";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderPasswd = encoder.encode(passwd);
		System.out.println(""+encoderPasswd);
	}

}
