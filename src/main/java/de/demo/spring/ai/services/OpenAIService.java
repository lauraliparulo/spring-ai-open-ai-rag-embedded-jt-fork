package de.demo.spring.ai.services;

import de.demo.spring.ai.model.Answer;
import de.demo.spring.ai.model.Question;

public interface OpenAIService {
	
	Answer getAnswer(Question question);

}
