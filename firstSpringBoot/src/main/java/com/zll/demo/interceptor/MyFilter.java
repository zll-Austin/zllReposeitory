package com.zll.demo.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("MyFilter >>>> init");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter >>>>>> doFilter");
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		System.out.println("MyFilter >>>>>> destroy");
	}
}
