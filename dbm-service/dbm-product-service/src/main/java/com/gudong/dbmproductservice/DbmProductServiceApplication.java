package com.gudong.dbmproductservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//识别 mapper接口的注解
@MapperScan("com.gudong.dbm.mapper")
@EnableDubbo
@SpringBootApplication
public class DbmProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmProductServiceApplication.class, args);
	}

}
