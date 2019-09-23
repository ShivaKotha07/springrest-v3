import { Component, OnInit } from '@angular/core';
import { User } from './app.user';
import { UserService } from './user.service';
import { Login } from './login';
import { Router } from '@angular/router';


 
@Component({
  selector: 'cg-app',
  templateUrl: './app.mobilecomponent.html',
 
})
export class UserListComponent implements OnInit {
 
  users: User[];
  
 
  constructor(private userService: UserService,private router: Router) {
  
  }

 
  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
    
  }

}