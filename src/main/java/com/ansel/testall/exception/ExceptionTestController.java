package com.ansel.testall.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {
	@RequestMapping("/myex")
	public String myException() throws Exception {
		throw new Exception("eeeeeeeeeeeee");
	}
}
