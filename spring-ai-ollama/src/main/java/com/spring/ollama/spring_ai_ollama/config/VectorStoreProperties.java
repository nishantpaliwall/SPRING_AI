package com.spring.ollama.spring_ai_ollama.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Getter
@Setter
@Configuration
public class VectorStoreProperties {
    @Value("${vectorstore.aiapp.vectorStorePath}")
    private String vectorStorePath;

    @Value("${vectorstore.aiapp.documentsToLoad}")
    private List<Resource> documentsToLoad;
}
