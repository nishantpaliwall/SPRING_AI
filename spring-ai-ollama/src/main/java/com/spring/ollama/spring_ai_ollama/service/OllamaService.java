package com.spring.ollama.spring_ai_ollama.service;

import com.spring.ollama.spring_ai_ollama.model.Answer;
import com.spring.ollama.spring_ai_ollama.model.Question;
import org.springframework.stereotype.Service;

@Service
public interface OllamaService {
    Answer getAnswer(Question question);
}

