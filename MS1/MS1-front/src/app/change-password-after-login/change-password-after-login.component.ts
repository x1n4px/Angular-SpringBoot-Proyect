import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';
@Component({
  selector: 'app-change-password-after-login',
  templateUrl: './change-password-after-login.component.html',
  styleUrls: ['./change-password-after-login.component.css']
})
export class ChangePasswordAfterLoginComponent {

  user: User = new User();
  constructor(private _service: RegistrationService, private _route: Router) { }

  ngOnInit() {
    console.log(this.user);
  }

  registerUser() {
    this._service.changePasswordRemote(this.user).subscribe(
      data => {
        alert("Successfully User is register?");
        this._route.navigate(['/login']);
      }, error => alert("Sorry User not register"));

  }



}
