package com.gudong.dbmbackground;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//无需自动装配datasource
public class DbmBackgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmBackgroundApplication.class, args);
	}

}
