package de.liparulo.ai.rag.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import de.liparulo.ai.rag.model.Answer;
import de.liparulo.ai.rag.model.Question;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value="vectorStoreOpenAIService")
public class OpenAIServiceVectoreStoreQueryImpl implements OpenAIService {

   final ChatClient chatClient;
   final SimpleVectorStore vectorStore;

   //@Value("classpath:/templates/rag-prompt-template.st")
   @Value("classpath:/templates/rag-prompt-template-meta.st")
   private Resource ragPromptTemplate;

   @Override
   public Answer getAnswer(Question question) {
       List<Document> documents = vectorStore.similaritySearch(SearchRequest
               .query(question.question()).withTopK(5));
       List<String> contentList = documents.stream().map(Document::getContent).toList();

       PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
       Prompt prompt = promptTemplate.create(Map.of("input", question.question(), "documents",
               String.join("\n", contentList)));

       contentList.forEach(System.out::println);

       ChatResponse response = chatClient.call(prompt);

       return new Answer(response.getResult().getOutput().getContent());
   }
}