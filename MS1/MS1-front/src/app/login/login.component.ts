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
  /*
  CIFRAR LA CLAVE ANTES DE ENVIARLA
  import * as bcrypt from 'bcryptjs';

  // ...

  // Obtener la contraseña del formulario de inicio de sesión
  let password = this.loginForm.get('password').value;

  // Cifrar la contraseña
  let hashedPassword = bcrypt.hashSync(password, 10);

  // Enviar la contraseña cifrada al servidor
  this.authService.loginUser({ emailId: email, password: hashedPassword })
      .subscribe(response => {
          // Manejar la respuesta del servidor
      });

*/




  loginUser() {
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log("response recieved");
        // Almacenamos la información del usuario en la sesión actual
        sessionStorage.setItem('currentUser', JSON.stringify(data));
        this._route.navigate(['/loginsuccess']);
      },
      error => {
        console.log("exception ocurred");
        this.msg = "Bad credentials, please enter valid emailid and password";
      }
    )
  }


  gotoregistration() {
    this._route.navigate(['/registration'])

  }
}

