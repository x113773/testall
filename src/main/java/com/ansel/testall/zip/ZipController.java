package com.ansel.testall.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;//注意引用的是apache的
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipController {
	@RequestMapping(value = "/zip", method = RequestMethod.GET)
	public void downloadZip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		String fileName = sdf.format(dt);//压缩包名字，已经过 "中文测试";
		File zip_file = new File(fileName + ".zip");
		ZipOutputStream out = null;
		FileInputStream in = null;
		List<File> srcList = new ArrayList<File>();
		srcList.add(new File(java.net.URLDecoder.decode("D:\\work\\q1.png", "UTF-8")));//修改成真实的文件地址，下同
		srcList.add(new File(java.net.URLDecoder.decode("D:\\work\\qr.png", "UTF-8")));
		try {
			out = new ZipOutputStream(new FileOutputStream(zip_file));
			out.setEncoding("UTF-8");
			byte[] buf = new byte[1024];
			Iterator it = srcList.iterator();
			while (it.hasNext()) {
				File file = (File) it.next();
				in = new FileInputStream(file);
				out.putNextEntry(new ZipEntry(file.getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.flush();
			out.close();
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(zip_file.getName().getBytes(), "ISO-8859-1"));
			ServletOutputStream sos = response.getOutputStream();
			FileInputStream fis = new FileInputStream(fileName + ".zip");
			IOUtils.copy(fis, sos);
			sos.flush();
			sos.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			zip_file.delete();//删掉临时生成，供用户下载的zip文件
		}

	}
}
