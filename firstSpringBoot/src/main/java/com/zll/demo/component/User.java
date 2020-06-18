package com.zll.demo.component;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="usermess")
public class User {
	private String name;
	private String address;
	private List<String> favorites;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<String> favorites) {
		this.favorites = favorites;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", address=" + address + ", favorites=" + favorites + "]";
	}
	
}
