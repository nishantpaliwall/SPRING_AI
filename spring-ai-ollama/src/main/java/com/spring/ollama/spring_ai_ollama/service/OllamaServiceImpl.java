package com.spring.ollama.spring_ai_ollama.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OllamaServiceImpl  implements OllamaService {

    @Autowired
    private ChatModel chatModel;

    @Value("classpath:templates/get-answer-prompt.st")
    private Resource getAnswerPrompt;

    @Override
    public String getResponse(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(getAnswerPrompt);
        Prompt prompt = promptTemplate.create(Map.of("question",question));
        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().toString();
    }
}
