package com.zll.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 添加全局数据
 * KEY      ---->  "info"
 * VALUE    ---->  map
 * @author zll
 *
 */
@ControllerAdvice
public class GlobalConfig {
	@ModelAttribute(value="info")
	public Map<String, String> userInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("user", "逗比一个");
		map.put("gender", "男");
		return map;
	}
	
	@InitBinder("b")
	public void init(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("b.");
	}
	@InitBinder("a")
	public void init2(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("a.");
	}
}
