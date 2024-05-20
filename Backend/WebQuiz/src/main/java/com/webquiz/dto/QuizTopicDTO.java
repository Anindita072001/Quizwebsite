package com.webquiz.dto;

import java.util.List;


public class QuizTopicDTO {

	
	String quizId;
	String quizTopic;
	List<QuizDTO> quizDTO;
	
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuizTopic() {
		return quizTopic;
	}
	public void setQuizTopic(String quizTopic) {
		this.quizTopic = quizTopic;
	}
	public List<QuizDTO> getQuizDTO() {
		return quizDTO;
	}
	public void setQuizDTO(List<QuizDTO> quizDTO) {
		this.quizDTO = quizDTO;
	}
	@Override
	public String toString() {
		return "QuizTopicDTO [quizId=" + quizId + ", quizTopic=" + quizTopic + ", quizDTO=" + quizDTO + "]";
	}
	
	
	
}
