package com.webquiz.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class QuizQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer queationId;
	String question;
	String q1;
	String q2;
	String q3;
	String q4;
	String q5;
	
	String ansKey;

	
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getQuizId() {
		return queationId;
	}

	public void setQuizId(Integer quizId) {
		this.queationId = quizId;
	}

	public String getQ1() {
		return q1;
	}

	public void setQ1(String q1) {
		this.q1 = q1;
	}

	public String getQ2() {
		return q2;
	}

	public void setQ2(String q2) {
		this.q2 = q2;
	}

	public String getQ3() {
		return q3;
	}

	public void setQ3(String q3) {
		this.q3 = q3;
	}

	public String getQ4() {
		return q4;
	}

	public void setQ4(String q4) {
		this.q4 = q4;
	}

	public String getQ5() {
		return q5;
	}

	public void setQ5(String q5) {
		this.q5 = q5;
	}

	public String getAnsKey() {
		return ansKey;
	}

	public void setAnsKey(String ansKey) {
		this.ansKey = ansKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ansKey, q1, q2, q3, q4, q5, queationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizQuestions other = (QuizQuestions) obj;
		return Objects.equals(ansKey, other.ansKey) && Objects.equals(q1, other.q1) && Objects.equals(q2, other.q2)
				&& Objects.equals(q3, other.q3) && Objects.equals(q4, other.q4) && Objects.equals(q5, other.q5)
				&& Objects.equals(queationId, other.queationId);
	}

	@Override
	public String toString() {
		return "QuizQuestions [quizId=" + queationId + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", q4=" + q4 + ", q5="
				+ q5 + ", ansKey=" + ansKey + "]";
	}
	
	
	
	
}
