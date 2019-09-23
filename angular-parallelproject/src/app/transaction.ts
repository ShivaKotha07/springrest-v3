import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import { Transaction } from './app.transaction';


 
@Component({
  selector: 'cg-app',
  templateUrl: './transaction.html',
 
})
export class TransactionComponent implements OnInit {
 
  trans: Transaction[];
  
 
  constructor(private userService: UserService,private router: Router) {
  
  }

 
  ngOnInit() {
    this.userService.findAllTransactions().subscribe(data => {
      this.trans = data;
    });
    
  }

}