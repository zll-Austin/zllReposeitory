package com.zll.demo.component;

import org.springframework.stereotype.Component;

@Component
public class GlobalDataBook {
	private String name;
	private String author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "GlobalDataBook [name=" + name + ", author=" + author + "]";
	}
	
	
}
