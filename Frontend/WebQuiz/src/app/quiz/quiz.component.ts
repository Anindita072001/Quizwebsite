import { Component, OnInit, OnDestroy } from '@angular/core';
import { QuizserviceService } from '../quizservice.service';
import { interval, Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {

  qData: any;
  questions: any;
  userAnswers: any[] = [];
  quizId: string;
  useremail: any;
  score = 0;
  scoreData: any;
  isQuizCompleted = false;
  timer: number = 30000; // 30 seconds in milliseconds
  timerSubscription: Subscription;
  currentQuestionIndex = 0;
  isOptionSelected = false;
  answerSubmitted = false;

  constructor(
    private service: QuizserviceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.quizId = this.route.snapshot.paramMap.get('id')!;
    this.useremail = this.route.snapshot.paramMap.get('useremail')!;
    this.loadQuiz();
  }

  ngOnDestroy() {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  loadQuiz() {
    this.service.getQuizById(this.quizId).subscribe(
      (data) => {
        this.qData = data;
        this.questions = data.quizDTO;
        this.resetQuiz();
        this.startTimer();
      },
      (error) => {
        console.error('Error loading quiz data:', error); // Debug
      }
    );
  }

  startTimer() {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
    this.timer = 30000; // Reset to 30 seconds in milliseconds
    this.timerSubscription = interval(1000).subscribe(() => {
      if (this.timer > 0) {
        this.timer -= 1000;
      } else {
        this.submitAnswer(null); // Automatically submit when the timer runs out
      }
    });
  }

  nextQuestion() {
    if (this.currentQuestionIndex < this.qData.quizDTO.length - 1) {
      this.currentQuestionIndex++;
      this.isOptionSelected = false;
      this.answerSubmitted = false;
      this.startTimer();
    } else {
      this.submitQuiz();
    }
  }

  previousQuestion() {
    if (this.currentQuestionIndex > 0) {
      this.currentQuestionIndex--;
      this.isOptionSelected = this.userAnswers[this.currentQuestionIndex] !== undefined;
      this.answerSubmitted = false;
      this.startTimer();
    }
  }

  submitAnswer(option: string) {
    this.userAnswers[this.currentQuestionIndex] = option;
    this.isOptionSelected = true;
    this.answerSubmitted = true;
    this.timerSubscription.unsubscribe();

    // Automatically go to the next question after 1 second
    setTimeout(() => {
      this.nextQuestion();
    }, 1000);
  }

  submitQuiz() {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
    this.calculateScore();
    this.isQuizCompleted = true;
  }

  calculateScore() {
    this.score = this.questions.reduce((acc, question, index) => {
      return acc + (this.userAnswers[index] === question.ansKey ? 1 : 0);
    }, 0);

    this.scoreData = {
      quizId: this.quizId,
      quizScore: this.score,
      userEmail: this.useremail
    };

    this.service.scoreUser(this.scoreData).subscribe(
      (data) => {
        this.qData = data;
        this.questions = data.quizDTO;
      },
      (error) => {
        console.error('Error submitting score data:', error);
      }
    );
  }

  reloadQuiz() {
    this.currentQuestionIndex = 0;
    this.userAnswers = [];
    this.score = 0;
    this.isQuizCompleted = false;
    this.isOptionSelected = false;
    this.answerSubmitted = false;
    this.loadQuiz();
  }

  resetQuiz() {
    this.currentQuestionIndex = 0;
    this.userAnswers = [];
    this.score = 0;
    this.isOptionSelected = false;
    this.answerSubmitted = false;
  }

  getOptionClass(option: string): string {
    if (!this.answerSubmitted) {
      return '';
    }
    if (option === this.questions[this.currentQuestionIndex].ansKey) {
      return 'correct';
    } else if (option === this.userAnswers[this.currentQuestionIndex]) {
      return 'incorrect';
    } else {
      return '';
    }
  }
}