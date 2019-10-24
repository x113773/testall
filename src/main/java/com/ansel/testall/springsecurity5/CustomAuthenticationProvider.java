package com.ansel.testall.springsecurity5;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ansel.testall.helper.SystemUtil;
import com.ansel.testall.mybatis.model.User;
import com.ansel.testall.mybatis.service.UserService;

 
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService userDetailsService;

	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		CustomAuthenticationToken authenticationToken = (CustomAuthenticationToken) authentication;

		String userName = (String) authenticationToken.getPrincipal();

		User user = (User) userDetailsService.loadUserByUsername(userName);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

		checkLogin(user, request);

		// 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
		CustomAuthenticationToken authenticationResult = new CustomAuthenticationToken(userDetails, userDetails.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	private void checkLogin(User userDetails, HttpServletRequest request) {

		String authCode = request.getParameter("authCode");
		// String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		Map<String, Object> result = new HashMap<String, Object>();
		String randomString = (String) request.getSession().getAttribute("randomString");

		if (!authCode.toUpperCase().equals(randomString)) {
			result.put("success", false);
			result.put("message", "验证码填写错误");
			throw new BadCredentialsException(result.get("message").toString());
		}

		String userName = userDetails.getUsername();
		password = password.trim();
		if (password.length() > 0) {
			if ("".equals(userDetails.getPassword()) || userDetails.getPassword() == null) {
				result.put("success", false);
				result.put("message", "用户名或密码错误");
				throw new BadCredentialsException(result.get("message").toString());
			}
			if (!userDetails.getPassword().equals(SystemUtil.MD5(password))) {
				result.put("success", false);
				result.put("message", "用户名或密码错误");
				throw new BadCredentialsException(result.get("message").toString());
			}
		}

		if ((Boolean) result.get("success") == false) {
			throw new BadCredentialsException(result.get("message").toString());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
		return CustomAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}