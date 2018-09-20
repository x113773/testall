package com.ansel.testall.xml;

import java.awt.PageAttributes.MediaType;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ansel.testall.helper.UploadHelper;

@RestController
public class XmlController {
	
    @RequestMapping(value = "/xml/response", method = RequestMethod.GET, produces ="application/xml")
	@ResponseBody
    public HelloBean xmlResponse() {
        return new HelloBean("1","Jack","22","male","nothing");
    }
    
    @RequestMapping(value = "xml/requset",consumes = "application/xml",produces ="application/xml",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> xmlRequset(@RequestBody HelloBean helloBean ) {
         Map<String, Object> param = new HashMap<String, Object>();
         param.put("id", helloBean.getId());
         param.put("name", helloBean.getName());
         param.put("age", helloBean.getAge());
         param.put("sex", helloBean.getSex());
         param.put("other", helloBean.getOther());
         System.out.println(param.toString());
         return param;
    }
    
    @RequestMapping(value = "/xml/upload", method = RequestMethod.POST)
	public Map<String, Object> xmlUpload(HttpServletRequest request,
			@RequestParam(value = "myFile", required = false) MultipartFile[] myFile){
    	Map<String, Object> result = new HashMap<String, Object>();
    	String path = "C:/opt/";
		UploadHelper.saveMultipleFiles(myFile, path);
		return result;
    }
}
