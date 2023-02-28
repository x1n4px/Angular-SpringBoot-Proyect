import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = 'http://localhost:8080/api/v1/user'; // URL de la API

  constructor(private http: HttpClient) { }

  getUserDetails() {
    return this.http.get(this.apiUrl);
  }
}
