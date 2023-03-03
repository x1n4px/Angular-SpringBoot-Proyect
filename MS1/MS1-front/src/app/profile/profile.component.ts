import { NumberSymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { User } from '../user';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})


export class ProfileComponent {
  currentUser: any;


  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser') || '{}');
  }

  changePassword() {
    this.router.navigate(['/change-password-after-login']);
  }


}
