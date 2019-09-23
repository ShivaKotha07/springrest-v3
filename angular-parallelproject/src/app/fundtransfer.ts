import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './user.service';
import { User } from './app.user';
import { UserListComponent } from './app.mobilecomponent';
 
@Component({
  selector: 'cg-app',
  templateUrl: './fundtransfer.html',
  styleUrls : ['./style.css']
})

export class FundTransferComponent {
 
    user : User;
    amount1: number;
    res:boolean;

  constructor(private router: Router,private userService: UserService) {
        this.user = new User();
        
  }

  onSubmit(userid:String,amount:number) {
     //console.log(amount);
     this.userService.checkUserId(userid).subscribe(result => 
      {
        this.res = result;
        console.log(this.res);
        if(this.res)
        {
          this.userService.fundtransfer(userid,amount).subscribe(data =>
            {
              this.amount1 = data;
              alert("Amount transferred Successfully.");
             // console.log(userid);
            });
        }
        else
        {
          alert("User does not exists with given userId");
        }
      });
}

homepage()
{
  this.router.navigate(['./menu']);
}

}