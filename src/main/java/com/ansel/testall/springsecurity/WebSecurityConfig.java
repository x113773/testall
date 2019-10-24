package com.ansel.testall.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.ansel.testall.mybatis.mapper.RoleMapper;
import com.ansel.testall.mybatis.mapper.UserMapper;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserMapper userMapper;

	@Autowired
	RoleMapper roleMapper;

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
		web.ignoring().antMatchers("/frame/**", "/img/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**", "/login/**").permitAll()//为了测试其他功能，设置“ /** ”允许所有请求
				// user权限可以访问的请求
				.antMatchers("/security/user").hasRole("user")
				// admin权限可以访问的请求
				.antMatchers("/security/admin").hasRole("admin")
				// SpEL表达式:需要拥有user权限，且进行了完全认证
				.antMatchers("/user/account").access("hasRole('user') and isFullyAuthenticated()")
				// 其他地址的访问均需验证权限（需要登录）
				.anyRequest().authenticated().and()
				// 添加验证码验证
				.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login_page")).and()
				.addFilterAt(rememberMeAuthenticationFilter(), RememberMeAuthenticationFilter.class)
				// 指定登录页面的请求路径
				.formLogin().loginPage("/login_page")
				// 登陆处理路径
				.loginProcessingUrl("/login").permitAll().and()
				// 退出请求的默认路径为logout，下面改为signout，
				// 成功退出登录后的url可以用logoutSuccessUrl设置
				.logout().deleteCookies("remember-me").logoutUrl("/signout").logoutSuccessUrl("/login_page").permitAll().and()
				// 开启rememberMe，设置一个私钥专供testall项目使用，注意与下面TokenBasedRememberMeServices的key保持一致
				// .rememberMe().key("testallKey").and()
				// 关闭csrf
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(new Md5PasswordEncoder()).and()
				.authenticationProvider(rememberMeAuthenticationProvider());
	}
	
	@Bean
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl(userMapper, roleMapper);
	}

	@Bean
	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
		MyUsernamePasswordAuthenticationFilter myFilter = new MyUsernamePasswordAuthenticationFilter();
		myFilter.setAuthenticationManager(authenticationManagerBean());
		myFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		myFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
		myFilter.setRememberMeServices(tokenBasedRememberMeServices());
		return myFilter;
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/login/success");
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login/failure");
	}

	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tbrms = new TokenBasedRememberMeServices("testallKey", userDetailsServiceImpl());
		// 设置cookie过期时间为2天
		tbrms.setTokenValiditySeconds(60 * 60 * 24 * 2);
		// 设置checkbox的参数名为rememberMe（默认为remember-me），注意如果是ajax请求，参数名不是checkbox的name而是在ajax的data里
		tbrms.setParameter("rememberMe");
		tbrms.setAlwaysRemember(false);
		return tbrms;
	}

	@Bean
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		RememberMeAuthenticationProvider rmap = new RememberMeAuthenticationProvider("testallKey");
		return rmap;
	}

	@Bean
	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
		RememberMeAuthenticationFilter myFilter = new RememberMeAuthenticationFilter(authenticationManagerBean(), tokenBasedRememberMeServices());
		return myFilter;
	}

}
