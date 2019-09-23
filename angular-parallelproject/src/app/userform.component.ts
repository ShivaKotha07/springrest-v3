import { Component,OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './user.service';
import { User } from './app.user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
 
@Component({
  selector: 'ng-app',
  templateUrl: './userform.component.html',
  styleUrls : ['./style.css']
})
export class UserFormComponent {
 
  user: User;
  userForm: FormGroup;
  res : boolean;
  
  constructor(private router: Router, private userService: UserService,private formBuilder: FormBuilder) {
    this.user = new User();
  }
  
  // onSubmit() {
  //   this.userService.save(this.user).subscribe(result => this.gotoUserList());
  // }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

  onSubmit(userId : String)
  {
    this.userService.checkUserId(userId).subscribe(result => 
      {
        this.res = result;
        console.log(this.res);
        if(this.res)
        {
          alert("UserId is already exists.")
        }
        else
        {
          this.userService.save(this.user).subscribe(result => 
            {
              if(!result)
              {
                alert("Account is created successfully.");
                this.router.navigate(['/login']);
              }
              else
              {
                alert("Error occured while creating user.");
              }
            });
        }
      });
  }
}