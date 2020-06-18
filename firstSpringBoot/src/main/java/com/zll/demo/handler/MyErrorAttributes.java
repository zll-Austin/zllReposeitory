package com.zll.demo.handler;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace){
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
		//errorAttributes.put("custommsg", "找不到啦");
		//errorAttributes.remove("error");
		return errorAttributes;
	}
}
