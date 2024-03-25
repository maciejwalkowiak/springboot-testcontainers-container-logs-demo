package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@EnableContainerLogs(debug = false)
@Import(TestDemoApplication.class)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
}
