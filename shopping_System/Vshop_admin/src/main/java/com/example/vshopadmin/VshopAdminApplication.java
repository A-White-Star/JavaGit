package com.example.vshopadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.vshopadmin.dao")
public class VshopAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(VshopAdminApplication.class, args);
	}

}
