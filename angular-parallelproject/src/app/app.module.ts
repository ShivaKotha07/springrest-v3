import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {UserListComponent} from "./app.mobilecomponent";

import {FormsModule,ReactiveFormsModule} 
from '@angular/forms';
import {HttpModule} from '@angular/http';
import {Routes, RouterModule} from '@angular/router';
import { HttpClientModule } from 
'@angular/common/http';
import { UserService } from './user.service';
import { UserFormComponent } from './userform.component';
import { LoginComponent } from './login.component';
import { MenuComponent } from './menu.component';
import { BalanceComponent } from './balance';
import { DepositComponent } from './deposit';
import { WithdrawComponent } from './withdraw';
import { FundTransferComponent } from './fundtransfer';
import { TransactionComponent } from './transaction';



const appRoutes:Routes=
[
    {path:"",redirectTo:"login",pathMatch:"full"},
    {path:"login", component:LoginComponent },
    {path:"users",component:UserListComponent},
    {path:"adduser", component: UserFormComponent },
    {path:"menu", component: MenuComponent } ,
    {path:"showbalance", component: BalanceComponent },
    {path:"deposit", component: DepositComponent },
    {path:"withdraw", component: WithdrawComponent },
    {path:"fundtransfer", component: FundTransferComponent },
    {path:"transactions", component: TransactionComponent } 
]
@NgModule({
    imports: [BrowserModule,HttpModule, HttpClientModule,
         FormsModule ,ReactiveFormsModule,
          RouterModule.forRoot(appRoutes) ],
    declarations: [
        AppComponent,UserListComponent,UserFormComponent,LoginComponent,MenuComponent,
        BalanceComponent,DepositComponent,WithdrawComponent,FundTransferComponent,
        TransactionComponent
       	],
    providers: [UserService ],
    bootstrap: [AppComponent]
})

export class AppModule { }