import { Component } from '@angular/core';
import { QuizserviceService } from '../quizservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quiz-topic',
  templateUrl: './quiz-topic.component.html',
  styleUrl: './quiz-topic.component.css'
})
export class QuizTopicComponent {

  qData:any;
  questions:any;
  useremail:any;
  constructor(private service:QuizserviceService,private route: ActivatedRoute){}


  ngOnInit(): void {
    this.useremail = this.route.snapshot.paramMap.get('useremail')!;
    this.service.getAllQuiz().subscribe(
      (data) => {
        this.qData= data; 
        this.questions = data.quizDTO;
        console.log("data=>",data);
       
      },
      (error) => {
        console.error("error=>",error); 
      }
    );
    
}
currentQuestionIndex = 0;

}
