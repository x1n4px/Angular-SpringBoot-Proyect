import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  user: User = new User();


  msg = '';
  constructor(private _service: RegistrationService, private _route: Router) { }

  ngOnInit() {
    console.log(this.user);
  }

  registerUser() {
    this._service.registerUserFromRemote(this.user).subscribe(
      data => {
        alert("Successfully User is register?");
        this._route.navigate(['/login']);
      }, error => alert("Sorry User not register"));

  }


}
