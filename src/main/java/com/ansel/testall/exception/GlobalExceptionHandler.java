package com.ansel.testall.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseResult ExceptionHandler(HttpServletRequest req, Exception e) {
		ResponseResult rr = new ResponseResult();
		rr.setReqUrl(req.getRequestURL().toString());
		rr.setRespCode("500");
		rr.setRespDesc(e + ":" + e.getMessage());
		e.printStackTrace();
		return rr;
	}
}