package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration(proxyBeanMethods = false)
public class TestDemoApplication {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        var postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
        return postgres;
    }

    @Bean
    @ServiceConnection
    LocalStackContainer localStackContainer() {
        return new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2.0"));
    }

    @Bean
    @ServiceConnection
    RabbitMQContainer rabbitContainer() {
        return new RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"));
    }

    public static void main(String[] args) {
        SpringApplication.from(DemoApplication::main).with(TestDemoApplication.class).run(args);
    }

}
