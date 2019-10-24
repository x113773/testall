package com.ansel.testall.springsecurity5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig5 extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomWebSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 忽略静态文件
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/main.html","/**/top.html","/**/bottom.html",
				"/js/**", "/frame/**", "/image/**","/img/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.apply(smsCodeAuthenticationSecurityConfig).and().authorizeRequests()
		.antMatchers("/", "/main.html", "/login/**", "/authCode", "/user/logout").permitAll()
		// 为了测试其他功能，设置“ /** ”允许所有请求
		// user权限可以访问的请求
				.antMatchers("/security/user").hasRole("partner_user")
				// admin权限可以访问的请求
				.antMatchers("/security/admin").hasRole("partner_admin")
				// 其他地址的访问均需验证权限（需要登录）
				.anyRequest().authenticated().and()
				// 指定登录页面的请求路径
				.formLogin().loginPage("/")
				// 登陆处理路径
				.loginProcessingUrl("/login").permitAll().and()
				// 退出请求的默认路径为logout，下面改为signout，
				// 成功退出登录后的url可以用logoutSuccessUrl设置
				.logout().logoutUrl("/user/logout").logoutSuccessUrl("/").permitAll().and()
				// 关闭csrf
				.csrf().disable();
		
		 http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
	        .accessDeniedHandler(new CustomAccessDeineHandler());       
 
	}


	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/login/success");
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login/failure");
	}


}
