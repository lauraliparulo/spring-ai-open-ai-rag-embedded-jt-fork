package de.liparulo.ai.rag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.liparulo.ai.rag.model.Answer;
import de.liparulo.ai.rag.model.Question;
import de.liparulo.ai.rag.services.OpenAIService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {

	@Autowired
	@Qualifier("simpleOpenAIservice")
	private OpenAIService simpleOpenAIservice;
	
	@Autowired
	@Qualifier("vectorStoreOpenAIService")
	private OpenAIService openAIServiceVectoreStoreQueryImpl;
	
	@PostMapping("/ask")
	public Answer askQuestion(@RequestBody Question question) {
		return simpleOpenAIservice.getAnswer(question);
	}
	
	
	/**
	 * Example "   "question": "what is the movie Spider-Man: No Way home about?"" will work, but just using "Spider-Man" no
	 * **/
	@PostMapping("/vector-store-ask")
	public Answer askQuestionVectorStore(@RequestBody Question question) {
		return openAIServiceVectoreStoreQueryImpl.getAnswer(question);
	}
}
