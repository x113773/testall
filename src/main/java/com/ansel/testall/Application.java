package com.ansel.testall;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.ansel.testall.mybatis.mapper")
public class Application extends SpringBootServletInitializer {

	/**
	 * Start
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	/**
	 * 查看当前的连接池和事务管理器
	 * 
	 * @param dataSource
	 * @param platformTransactionManager
	 * @return
	 */
	@Bean
	public Object testBean(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
		System.out.println("current dataSource: " + dataSource.getClass().getName());
		System.out.println("current platformTransactionManager: " + platformTransactionManager.getClass().getName());
		return new Object();
	}
}