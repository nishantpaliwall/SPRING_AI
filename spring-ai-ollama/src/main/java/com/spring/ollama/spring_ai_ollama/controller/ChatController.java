package com.spring.ollama.spring_ai_ollama.controller;

import com.spring.ollama.spring_ai_ollama.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private OllamaService ollamaServiceImpl;
    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value="question") String question) {
        String answer= ollamaServiceImpl.getResponse(question);
        return ResponseEntity.ok().body(answer);
    }
}
