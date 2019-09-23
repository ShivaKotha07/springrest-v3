import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { Login } from './login';
import { UserService } from './user.service';
 
@Component({
  selector: 'cg-app',
  templateUrl: './menu.component.html',
  styleUrls : ['./style.css']
})

export class MenuComponent {
 

  constructor(private router: Router,private userService: UserService) {
 
  }

  showBalance() {
     //alert("dfhdfh");
    this.router.navigate(['/showbalance']);
  }

  depositAmount()
  {
    this.router.navigate(['/deposit']);
  }

  withdrawAmount()
  {
    this.router.navigate(['/withdraw']);
  }

  fundTransfer()
  {
    this.router.navigate(['/fundtransfer']);
  }

  logout()
  {
    this.router.navigate(['/login']);
  }

  showTransaction()
  {
    this.router.navigate(['/transactions']);
  }
}