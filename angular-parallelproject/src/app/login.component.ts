import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { Login } from './login';
import { UserService } from './user.service';
import { User } from './app.user';
 
@Component({
  selector: 'cg-app',
  templateUrl: './login.component.html',
  styleUrls : ['./style.css']
})

export class LoginComponent {
 
  user : User;
  login : Login;
  res : boolean;
 
  constructor(private router: Router,private userService: UserService) {
    this.login = new Login();
    this.user = new User();
  }

  // onLogin() {
  //    //alert("dfhdfh");
  //   this.router.navigate(['/menu']);
  // }


  onLogin(userId:String,password:String) {
   // console.log(userId);
    this.user.userId = userId;
    this.user.password = password;
    this.userService.login(userId,password).subscribe(data =>
      {
        this.res = data;
        //console.log(data);
        if(this.res)
        {
          this.router.navigate(['/menu']);
        }
        else
        {
          alert("Wrong credentials..Please try again.")
          this.router.navigate(['/login']);
        }
      });
 }

  register()
  {
    this.router.navigate(['/adduser']);
  }
}