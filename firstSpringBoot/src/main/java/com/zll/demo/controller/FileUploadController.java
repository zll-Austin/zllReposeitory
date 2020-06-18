package com.zll.demo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@Controller
@RestController
public class FileUploadController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	@PostMapping("/upload")
	//@ResponseBody
	public String upload(MultipartFile uploadFile, HttpServletRequest req) {
		//String realPth = req.getSession().getServletContext().getRealPath("/uploadFile/");
		String realPath = "E:\\WorkTable\\Workspaces\\SprinBoot\\src\\main\\resources\\static\\uploadFile\\";
		String format = sdf.format(new Date());
		File folder = new File(realPath+format);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		String oldName = uploadFile.getOriginalFilename();
		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
		try {
			uploadFile.transferTo(new File(folder, newName));
			String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format+ "/" + newName;
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传失败";
	}
	
	@PostMapping("/uploads")
	//@ResponseBody
	public String uploads(MultipartFile[] uploadFiles, HttpServletRequest req) {
		//String realPth = req.getSession().getServletContext().getRealPath("/uploadFile/");
		String realPath = "E:\\WorkTable\\Workspaces\\SprinBoot\\src\\main\\resources\\static\\uploadFiles\\";
		String format = sdf.format(new Date());
		File folder = new File(realPath+format);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		String filePath = "";
			try {
				for(MultipartFile uploadFile : uploadFiles) {
					String oldName = uploadFile.getOriginalFilename();
					if(!oldName.isEmpty()) {
						String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
						uploadFile.transferTo(new File(folder, newName));
						filePath += req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFiles/" + format+ "/" + newName;
					}
				}
				return filePath;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "上传失败";
	}
}
