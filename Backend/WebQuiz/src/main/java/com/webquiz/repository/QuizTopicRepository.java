package com.webquiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.webquiz.entity.QuizTopic;

public interface QuizTopicRepository extends CrudRepository<QuizTopic, String>{
	
	public Optional<QuizTopic> findByQuizId(String quizId);

	

}
