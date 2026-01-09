package com.spring.ollama.spring_ai_ollama.controller;

import com.spring.ollama.spring_ai_ollama.model.Answer;
import com.spring.ollama.spring_ai_ollama.model.Question;
import com.spring.ollama.spring_ai_ollama.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final OllamaService ollamaService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return ollamaService.getAnswer(question);
    }

}