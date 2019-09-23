import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { Login } from './login';
import { UserService } from './user.service';
 
@Component({
  selector: 'cg-app',
  templateUrl: './app.component.html'
})

export class AppComponent {
 

//  login : Login;
 
  constructor(private router: Router,private userService: UserService) {
 //   this.login = new Login();
  }

  // loginForm = new FormGroup
  // ({
  //     fname : new FormControl('snehal',[Validators.required,Validators.minLength(4),
  //     Validators.maxLength(10)])
     
  // })


}