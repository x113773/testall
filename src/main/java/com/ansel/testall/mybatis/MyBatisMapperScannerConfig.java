/*package com.ansel.testall.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解，而这个注解只能放在类上，所以...
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		//因为只有一个sqlSessionFactory，所以下面这个可以不用设置
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.ansel.testall.mybatis.mapper");
		return mapperScannerConfigurer;
	}

}
*/