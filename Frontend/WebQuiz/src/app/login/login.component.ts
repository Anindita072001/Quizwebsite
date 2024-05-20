import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuizserviceService } from '../quizservice.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userLogin : FormGroup;
  isInnvalid:boolean=false;
  invalidMsg:any;
  constructor(private service:QuizserviceService, private fb:FormBuilder,private router: Router) {
   
   }
 ngOnInit():void {
   this.userLogin=this.fb.group({
     email:['', [Validators.required, Validators.email]],
     password:['', [Validators.required, Validators.minLength(6)]]
   })
 }


 loginUser(){
  const ld = this.userLogin.value;
  this.service.userLogin(ld).subscribe({
    next:(response)=>{console.log(response.body);
      window.sessionStorage.setItem('loggedIn', 'true');
      window.sessionStorage.setItem('userId',ld.email);
     
      this.router.navigate(['/allQuiz',ld.email])
      // window.location.href = '/post/All';
     
     
    },
    error:(error:HttpErrorResponse)=>{console.log(error.error);
      this.invalidMsg = error.error;
      this.isInnvalid = true;
    }
      
    
})
}

}
