package com.webquiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.webquiz.entity.QuizTopic;
import com.webquiz.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer>{
	public Optional<UserEntity> findByEmail(String email);
	
	
	 
}
