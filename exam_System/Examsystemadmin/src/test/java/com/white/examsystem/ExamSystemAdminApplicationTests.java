package com.white.examsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ExamSystemAdminApplicationTests {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		System.out.println(passwordEncoder.encode("123456"));
	}

}
