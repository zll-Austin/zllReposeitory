package com.zll.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zll.demo.aop.service.UserService;
import com.zll.demo.component.User;
import com.zll.demo.component.Users;

//@RestController /* @Controller + @ResponseBody*/
@Controller
public class UserController {
	@Autowired
	Users users;
	@Autowired
	User user;
	@GetMapping("/users")
	@ResponseBody
	public String users() {
		return users.toString();
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user() {
		System.out.println(System.getProperties());
		return user.toString();
	}
	
	@GetMapping("/person")
	@ResponseBody
	public ModelAndView persons(){
		List<User> users = new ArrayList<>();
		User u1 = new User();
		u1.setName("小明");
		u1.setAddress("黄河以北");
		List<String> favorites1 = new ArrayList<>();
		favorites1.add("游泳");
		favorites1.add("跑步");
		u1.setFavorites(favorites1);
		
		User u2 = new User();
		u2.setName("小明");
		u2.setAddress("黄河以北");
		List<String> favorites2 = new ArrayList<>();
		favorites2.add("游泳");
		favorites2.add("跑步");
		u1.setFavorites(favorites2);
		
		users.add(u1);
		users.add(u2);
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("users");
		return mv;
	}
	
	@Autowired
	UserService userService;

	@GetMapping("/getUserById")
	@ResponseBody
	public String getUserById(Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/deleteUserById")
	@ResponseBody
	public void deleteUserById(Integer id) {
		userService.deleteUserById(id);
	}
}


