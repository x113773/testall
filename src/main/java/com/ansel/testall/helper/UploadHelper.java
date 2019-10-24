package com.ansel.testall.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class UploadHelper {

	public static void saveFile(MultipartFile file, String path) throws Exception {
		byte[] bytes = file.getBytes();
		File nf = new File(path);
		if (!nf.exists()) {
			if (!nf.mkdirs()) {
				System.out.println("创建文件夹\"" + path + "\"失败");
			}
		}
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + file.getOriginalFilename())));
		stream.write(bytes);
		stream.close();

	}

	public static void downloadFile(HttpServletResponse response, HttpServletRequest request, String name, String path) throws IOException {
		try {
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);
			
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") < 0) {  
				name = new String(name.getBytes("UTF-8"), "ISO8859-1");   
			}else{
				name = java.net.URLEncoder.encode(name, "UTF-8"); 
			}
			response.setHeader("Content-Disposition", "attachment;filename="+name);
			OutputStream out = response.getOutputStream();
			//创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			//循环将输入流中的内容读取到缓冲区当中
			while((len=in.read(buffer))>0){
			//输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
			}
			//关闭文件输入流
			in.close();
			//关闭输出流
			out.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void saveMultipleFiles(MultipartFile[] files, String path) {
		if(files!=null&&files.length>0){
			try {
			for (MultipartFile multipartFile : files) {
					byte[] bytes = multipartFile.getBytes();
					File nf = new File(path);
					if (!nf.exists()) {
						if (!nf.mkdirs()) {
							System.out.println("创建文件夹\"" + path + "\"失败");
						}
					}
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + multipartFile.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String downloadImage(String file_path, HttpServletResponse response) {
		try {
			File file = new File(file_path);
			FileInputStream in = new FileInputStream(file);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			//创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			//循环将输入流中的内容读取到缓冲区当中
			while((len=in.read(buffer))>0){
			//输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
			}
			//关闭文件输入流
			in.close();
			//关闭输出流
			out.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
		}
		return null;
	}

}
