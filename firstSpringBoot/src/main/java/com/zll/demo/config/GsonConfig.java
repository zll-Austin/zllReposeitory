package com.zll.demo.config;

import java.lang.reflect.Modifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class GsonConfig {
	/* 开发者提供一个GsonHttpMessageConverter实例就可以不使用GSON默认的GsonHttpMessageConverter实例 */
	@Bean
	GsonHttpMessageConverter gsonHttpMessageConverter() {
		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		GsonBuilder builder = new GsonBuilder();
		//设置GSON解析时日期的格式
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		//解析时修饰符为protect的字段会被过滤掉
		builder.excludeFieldsWithModifiers(Modifier.PROTECTED);
		Gson gson = builder.create();
		converter.setGson(gson);
		return converter;
	}
}
