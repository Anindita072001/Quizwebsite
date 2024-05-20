package com.webquiz.dto;

public class ScoreDTO {
	Integer scoreId;
	String quizId;
	Integer quizScore;
	String userEmail;
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public Integer getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(Integer quizScore) {
		this.quizScore = quizScore;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
