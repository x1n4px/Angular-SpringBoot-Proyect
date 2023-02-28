import { NumberSymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { HttpClient } from '@angular/common/http';

import { User } from '../user';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})


export class ProfileComponent {
  user = new User();
  welcomeMessage = '';
  
  constructor(private http: HttpClient) {
    this.getUserDetails();
  }
  
  getUserDetails() {
    this.http.get<User>('/api/profile').subscribe(
      user => {
        // mostramos un mensaje de bienvenida al usuario
        this.welcomeMessage = 'Bienvenido, ' + user.emailId;
        // actualizamos la propiedad user con el objeto User
        this.user = user;
      },
      error => {
        // mostramos un mensaje de error si la sesión ha expirado
        this.welcomeMessage = 'Por favor inicia sesión primero.';
      }
    );
  }
  
}
