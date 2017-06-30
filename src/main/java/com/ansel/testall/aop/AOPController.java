package com.ansel.testall.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOPController {

	@RequestMapping(value = "/aop", method = RequestMethod.GET)
	public String testAOP() {
		return "this is a AOP test.";
	}

}
