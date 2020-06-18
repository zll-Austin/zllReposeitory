package com.zll.demo.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局异常处理
 * @author zll
 *
 */
//@ControllerAdvice
//@Controller
public class CustomExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/htmlcharset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("上传文件大小超出范围!");
		out.flush();
		out.close();
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView uploadException(MaxUploadSizeExceededException e) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "上传文件大小超出范围!");
		mv.setViewName("/error.html");
		return mv;
	}
	@ExceptionHandler(FileSizeLimitExceededException.class)
	public ModelAndView uploadException(FileSizeLimitExceededException e) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "上传文件大小超出范围!");
		mv.setViewName("/error.html");
		return mv;
	}
}
