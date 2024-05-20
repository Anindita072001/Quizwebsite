package com.webquiz.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Override
	public int hashCode() {
		return Objects.hash(quizId, quizScore, scoreId, userEmail);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return Objects.equals(quizId, other.quizId) && Objects.equals(quizScore, other.quizScore)
				&& Objects.equals(scoreId, other.scoreId) && Objects.equals(userEmail, other.userEmail);
	}
	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", quizId=" + quizId + ", quizScore=" + quizScore + ", userEmail="
				+ userEmail + "]";
	}
	
	
	
}
