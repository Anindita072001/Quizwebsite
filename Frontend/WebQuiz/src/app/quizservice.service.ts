import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class QuizserviceService {

  constructor(private http:HttpClient) { }

  getQuizById(quizId:String):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'crossOrigin': 'true' 
    });
    return this.http.get<any>(`http://localhost:8080/webquiz/getQuiz/${quizId}`,{headers});
  }


  getAllQuiz():Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'crossOrigin': 'true' 
    });
    return this.http.get<any>("http://localhost:8080/webquiz/allQuizs",{headers});
  }


  userRegistration(userData:any):Observable<any>{
  
    return this.http.post("http://localhost:8080/webquiz/registerUser",userData,
    {
      observe: 'response',
      responseType: 'text' as 'json' 
    }
    );
  }

  userLogin(userData:any):Observable<any>{
  
    return this.http.post("http://localhost:8080/webquiz/login",userData,
    {
      observe: 'response',
      responseType: 'text' as 'json' 
    }
    );
  }

  scoreUser(userData:any):Observable<any>{
  
    return this.http.post("http://localhost:8080/webquiz/quizscore",userData,
    {
      observe: 'response',
      responseType: 'text' as 'json' 
    }
    );
  }





  
}
