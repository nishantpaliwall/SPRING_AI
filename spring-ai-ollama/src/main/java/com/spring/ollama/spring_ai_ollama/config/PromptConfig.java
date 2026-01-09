package com.spring.ollama.spring_ai_ollama.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ai.chat.prompt.PromptTemplate;

@Configuration
public class PromptConfig {

    @Bean
    public PromptTemplate getAnswerPromptTemplate(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:templates/get-answer-prompt.st");
        return new PromptTemplate(resource);
    }
}

