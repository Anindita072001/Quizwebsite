package com.webquiz.service;

import java.util.List;

import com.webquiz.dto.QuizTopicDTO;
import com.webquiz.dto.ScoreDTO;
import com.webquiz.dto.UserDTO;
import com.webquiz.exception.WebQuizException;

public interface UserService {
	public Integer adduser(UserDTO user);
	public UserDTO getUser(String email);
	public List<UserDTO> getAllUser();
	public void updateUser(Integer userId,UserDTO userdto) throws Exception;
	public void deleteUser(String email);
	public void loginUser(String email,String password) throws WebQuizException;
	
	public String addQuiz(QuizTopicDTO qtDTO) throws WebQuizException;
	
	public QuizTopicDTO getQuiz(String quizId)throws WebQuizException;
	
	public Integer calculateScore(ScoreDTO s) throws WebQuizException;
	
	public List<QuizTopicDTO> getAllQuiz() throws WebQuizException;
}
