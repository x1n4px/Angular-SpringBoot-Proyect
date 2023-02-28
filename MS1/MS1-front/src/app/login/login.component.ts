import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  msg = '';
  constructor(private _service: RegistrationService, private _route: Router) { }

  ngOnInit() {

  }

  loginUser() {
    console.log(this.user);
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log("response recieved"),
        this._route.navigate(['/loginsuccess'])
      },
      error => {
        console.log("exception ocurred");
        this.msg = "Bad credentials, please enter valid emailid and password";
      }
    )
  }

  gotoregistration(){
    this._route.navigate(['/registration'])

  }
}

