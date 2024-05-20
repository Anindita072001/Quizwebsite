package com.webquiz.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class QuizTopic {

	@Id
	String quizId;
	String quizTopic;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="quizId")
	List<QuizQuestions> quiza;

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

	public List<QuizQuestions> getQuiza() {
		return quiza;
	}

	public void setQuiza(List<QuizQuestions> quiza) {
		this.quiza = quiza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(quizId, quizTopic, quiza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizTopic other = (QuizTopic) obj;
		return Objects.equals(quizId, other.quizId) && Objects.equals(quizTopic, other.quizTopic)
				&& Objects.equals(quiza, other.quiza);
	}

	@Override
	public String toString() {
		return "QuizTopic [quizId=" + quizId + ", quizTopic=" + quizTopic + ", quiza=" + quiza + "]";
	}
	
	
	
}
