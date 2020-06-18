package com.zll.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corsbook")
public class CorsBookController {
	
	@PostMapping("/")
	@CrossOrigin(value="http://localhost:8081", maxAge=1800, allowedHeaders="*")
	public String addBook(String name) {
		return "receive:" + name;
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(value="http://localhost:8081", maxAge=1800, allowedHeaders="*")
	public String deleteBookById(@PathVariable Long id) {
		return String.valueOf(id);
	}
	
}
