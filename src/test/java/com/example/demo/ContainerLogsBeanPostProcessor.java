package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

public class ContainerLogsBeanPostProcessor implements BeanPostProcessor {
    private final boolean debug;

    public ContainerLogsBeanPostProcessor(boolean debug) {
        this.debug = debug;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Container<?> container) {
            container.withLogConsumer(new StdOutConsumer<>(beanName));

            if (debug) {
                if (container instanceof PostgreSQLContainer<?> postgres) {
                    postgres.setCommand("postgres", "-c", "fsync=off", "-c", "log_statement=all");
                } else if (container instanceof LocalStackContainer localstack) {
                    localstack.withEnv("DEBUG", "1");
                }
            }
        }
        return bean;
    }
}
