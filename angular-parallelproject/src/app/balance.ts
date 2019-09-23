import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './user.service';
import { User } from './app.user';
import { UserListComponent } from './app.mobilecomponent';
 
@Component({
  selector: 'cg-app',
  templateUrl: './balance.html',
  styleUrls : ['./style.css']
})

export class BalanceComponent {
 
    user : User;
    balance: number;

  constructor(private router: Router,private userService: UserService) {
        this.user = new User();
        
  }

  showbalance() {
    // console.log(userId);
   // this.user.userId = userId;
     this.userService.showBalance().subscribe(data =>
       {
         this.balance = data;
         console.log(this.balance);
       });
  }
  homepage()
  {
    this.router.navigate(['./menu']);
  }
}