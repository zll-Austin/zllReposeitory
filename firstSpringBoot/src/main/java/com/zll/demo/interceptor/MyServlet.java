package com.zll.demo.interceptor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my")
public class MyServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3241560822038097240L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("name >>>>>> "+req.getParameter("name"));
	}
}
