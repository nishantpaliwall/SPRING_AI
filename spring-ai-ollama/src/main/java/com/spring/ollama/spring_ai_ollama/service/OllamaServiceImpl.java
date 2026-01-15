package com.spring.ollama.spring_ai_ollama.service;

import com.spring.ollama.spring_ai_ollama.model.Answer;
import com.spring.ollama.spring_ai_ollama.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OllamaServiceImpl  implements OllamaService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final PromptTemplate promptTemplate;

    @Autowired
    public OllamaServiceImpl(ChatClient chatClient, SimpleVectorStore vectorStore, PromptTemplate promptTemplate) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                        .query(question.question())
                        .topK(3)
                .build());
        List<String> documentList = documents.stream().map(doc->doc.getFormattedContent()).toList();
        Prompt prompt = promptTemplate.create(Map.of(
                "input", question.question(),
                "documents", String.join("\n",documentList)
        ));

        return new Answer(chatClient.prompt(prompt).call().content());
    }
}
