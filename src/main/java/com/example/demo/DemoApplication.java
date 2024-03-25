package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(SqsAsyncClient sqsAsyncClient, JdbcClient jdbcClient) {
		return args -> {
			System.out.println(jdbcClient.sql("select * from messages")
					.query().listOfRows());
			System.out.println(sqsAsyncClient.listQueues().join());
		};
	}
}
