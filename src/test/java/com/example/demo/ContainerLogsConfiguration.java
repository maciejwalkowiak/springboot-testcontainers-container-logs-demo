package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

@TestConfiguration
public class ContainerLogsConfiguration implements ImportAware {

    private AnnotationAttributes enableContainerLogs;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableContainerLogs = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableContainerLogs.class.getName()));
    }

    @Bean
    ContainerLogsBeanPostProcessor containerLogsBeanPostProcessor() {
        var debug = this.enableContainerLogs.getBoolean("debug");
        return new ContainerLogsBeanPostProcessor(debug);
    }
}
