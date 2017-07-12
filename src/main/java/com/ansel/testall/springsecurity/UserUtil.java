package com.ansel.testall.springsecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具类
 */
public class UserUtil {
	/*
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static MyUserDetails getCurrentUser() {
		MyUserDetails user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal != null && principal instanceof MyUserDetails) {
			user = (MyUserDetails) principal;
		}
		return user;
	}
}
