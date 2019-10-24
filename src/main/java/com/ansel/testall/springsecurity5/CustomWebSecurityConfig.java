package com.ansel.testall.springsecurity5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.ansel.testall.mybatis.mapper.RoleMapper;
import com.ansel.testall.mybatis.mapper.UserMapper;
import com.ansel.testall.mybatis.service.UserService;

@Component
public class CustomWebSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	UserMapper userMapper;

	@Autowired
	RoleMapper roleMapper;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter smsCodeAuthenticationFilter = new CustomAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		http.authenticationProvider(smsCodeAuthenticationProvider()).addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	CustomAuthenticationProvider smsCodeAuthenticationProvider() {
		CustomAuthenticationProvider smsCodeAuthenticationProvider = new CustomAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl());
		smsCodeAuthenticationProvider.setUserService(userService);
		return smsCodeAuthenticationProvider;
	}

	public CustomUserDetailsServiceImpl userDetailsServiceImpl() {
		return new CustomUserDetailsServiceImpl(userMapper, roleMapper);
	}
}
