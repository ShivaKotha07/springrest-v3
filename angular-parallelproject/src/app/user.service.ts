import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from './app.user';
import { Observable } from 'rxjs';
import { Transaction } from './app.transaction';
 
@Injectable()
export class UserService {
 
  private usersUrl: string;
  private usersUrl1: string;
  private usersUrl2: string;
  private usersUrl3: string;
  private usersUrl4: string;
  private usersUrl5: string;
  private usersUrl6: string;
  private usersUrl7: string;
  private usersUrl8: string;
 
  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:9097/user/';
    this.usersUrl1 = 'http://localhost:9097/user/create';
    this.usersUrl2 = 'http://localhost:9097/user/login';
    this.usersUrl3 = 'http://localhost:9097/user/findBalance';
    this.usersUrl4 = 'http://localhost:9097/user/deposit';
    this.usersUrl5 = 'http://localhost:9097/user/withdraw';
    this.usersUrl6 = 'http://localhost:9097/user/fundtransfer';
    this.usersUrl7 = 'http://localhost:9097/user/showtransaction';
    this.usersUrl8 = 'http://localhost:9097/user/validateUserId';
  }
 
  userId : String;
  password : String;

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }
 
  public save(user: User) {
    return this.http.post<User>(this.usersUrl1, user);
  }

  public login(userId:String,password:String) :Observable<any> {
   // console.log(userId," ",password);
   this.userId = userId;
    return this.http.get<any>(this.usersUrl2+"/"+userId+"/"+password);
  }

  public showBalance() :Observable<any> {
   // console.log(this.userId);
    return this.http.get<any>(this.usersUrl3+"/"+this.userId);
  }

  public deposit(amount:number) :Observable<any> {
    // console.log(this.userId);
     return this.http.get<any>(this.usersUrl4+"/"+this.userId+"/"+amount);
   }

   public withdraw(amount:number) :Observable<any> {
    // console.log(this.userId);
     return this.http.get<any>(this.usersUrl5+"/"+this.userId+"/"+amount);
   }

   public fundtransfer(userId1:String,amount:number) :Observable<any> {
     //console.log(this.userId);
     console.log(userId1);
     return this.http.get<any>(this.usersUrl6+"/"+this.userId+"/"+userId1+"/"+amount);
   }

   public findAllTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.usersUrl7+"/"+this.userId);
  }

  public checkUserId(userId:String) :Observable<any> {
    return this.http.get<any>(this.usersUrl8+"/"+userId);
  }

}