import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuizserviceService } from '../quizservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {

  
  userReg : FormGroup;
   constructor(private service:QuizserviceService, private fb:FormBuilder,private router: Router) {
    
    }
  ngOnInit():void {
    this.userReg=this.fb.group({
      email:['', [Validators.required, Validators.email]],
      phoneNo:['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      password:['', [Validators.required, Validators.minLength(6)]]
    })
  }


  public register(){
    const dData =this.userReg.value;
    console.log(dData);
   
    
    this.service.userRegistration(dData).subscribe({
     next:(success)=>{console.log(success);
        this.router.navigate([""]);
     },
     error:(error)=>console.log(error)
     
    }
     
    )
     
   }


}
