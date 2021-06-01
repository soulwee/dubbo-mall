package com.gudong.dbmsearchservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.gudong.dbm.mapper")
public class DbmSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmSearchServiceApplication.class, args);
	}

}
