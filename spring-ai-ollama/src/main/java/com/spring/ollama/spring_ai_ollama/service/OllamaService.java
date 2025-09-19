package com.spring.ollama.spring_ai_ollama.service;

import org.springframework.stereotype.Service;

@Service
public interface OllamaService {
    public String getResponse(String question);
}
