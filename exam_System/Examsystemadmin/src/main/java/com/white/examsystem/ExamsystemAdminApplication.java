package com.white.examsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.white.examsystem.dao")
public class ExamsystemAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsystemAdminApplication.class, args);
	}

}
