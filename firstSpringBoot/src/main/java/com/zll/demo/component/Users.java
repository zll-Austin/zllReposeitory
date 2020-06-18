package com.zll.demo.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="users")
public class Users{
	@Autowired
	User user;
	private List<User> usermess;

	public List<User> getUsermess() {
		return usermess;
	}

	public void setUsermess(List<User> usermess) {
		this.usermess = usermess;
	}

	@Override
	public String toString() {
		return "Users [usermess=" + usermess + "]";
	}
	
}
