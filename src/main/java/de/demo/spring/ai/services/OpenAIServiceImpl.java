package de.demo.spring.ai.services;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.demo.spring.ai.model.Answer;
import de.demo.spring.ai.model.Question;
import lombok.RequiredArgsConstructor;

@Service(value="simpleOpenAIservice")
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService{
	
	@Autowired
	final ChatClient chatClient;

	@Override
	public Answer getAnswer(Question question) {
		PromptTemplate promptTemplate = new PromptTemplate(question.question());
		Prompt prompt = promptTemplate.create();
		

		ChatResponse response = chatClient.call(prompt);

		// TODO Auto-generated method stub
		return new Answer(response.getResult().getOutput().getContent());
	}

}
