package com.zll.demo.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring boot!";
	}
	
	@GetMapping("/helloGolbal")
	@ResponseBody
	public void helloGlobal(Model model) {
		Map<String, Object> map = model.asMap();
		Set<String> keyset = map.keySet();
		Iterator<String> iterator = keyset.iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			System.out.println(key+">>>>>>>>>>>"+value);
		}
	}
	
	@GetMapping("/hello1")
	public String hello1() {
		return "hello spring boot ###### 1111111!";
	}
	
	@GetMapping("/hello2")
	public String hello2() {
		return "hello spring boot ###### 2222222!";
	}
	
	
	@GetMapping("/admin/hello")
	public String admin() {
		return "hello admin !!";
	}
	@GetMapping("/user/hello")
	public String user() {
		return "hello user !!";
	}
	@GetMapping("/db/hello")
	public String dba() {
		return "hello dba !!";
	}
}
 