package com.spring.ollama.spring_ai_ollama.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Slf4j
@Configuration
public class VectorStoreConfig {

    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
        log.info("Initializing SimpleVectorStore");
        SimpleVectorStore store = SimpleVectorStore.builder(embeddingModel).build();

        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());
        if (vectorStoreFile.exists()) {
            log.info("Loading existing vector store from {}", vectorStoreFile.getAbsolutePath());
            store.load(vectorStoreFile);
            return store;
        }else {
            log.debug("Loading documents into vector store");
            vectorStoreProperties.getDocumentsToLoad().forEach(resource -> {
                try {
                    log.info("Loading document: {}", resource.getFilename());
                    TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
                    List<Document> documents = tikaDocumentReader.get();

                    TextSplitter textSplitter = new TokenTextSplitter();
                    List<Document> splitDocuments = textSplitter.apply(documents);

                    store.add(splitDocuments);

                } catch (Exception e) {
                    log.error("Failed to load document: {}", resource.getFilename(), e);
        }

    });
            log.info("Saving vector store to {}", vectorStoreFile.getAbsolutePath());
            store.save(vectorStoreFile);
            return store;
        }
    }
}
