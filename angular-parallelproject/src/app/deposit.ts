import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './user.service';
import { User } from './app.user';
import { UserListComponent } from './app.mobilecomponent';
 
@Component({
  selector: 'cg-app',
  templateUrl: './deposit.html',
  styleUrls : ['./style.css']
})

export class DepositComponent {
 
    user : User;
    amount1: number;

  constructor(private router: Router,private userService: UserService) {
        this.user = new User();
        
  }

  onSubmit(amount:number) {
     //console.log(amount);
    
     this.userService.deposit(amount).subscribe(data =>
       {
         this.amount1 = data;
         alert("Amount Deposited Successfully.")
        // console.log(this.amount1);
       });
  }

  homepage()
  {
    this.router.navigate(['./menu']);
  }
}