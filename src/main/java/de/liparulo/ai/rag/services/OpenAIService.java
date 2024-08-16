package de.liparulo.ai.rag.services;

import de.liparulo.ai.rag.model.Answer;
import de.liparulo.ai.rag.model.Question;

public interface OpenAIService {
	
	Answer getAnswer(Question question);

}
