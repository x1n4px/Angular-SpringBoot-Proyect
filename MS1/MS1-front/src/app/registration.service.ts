import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private baseURL = "http://localhost:8080/api/v1/";
  private getEmailURL = "http://localhost:8080/api/v1//profile"

  constructor(private _http: HttpClient) { }

  public loginUserFromRemote(user: User): Observable<any> {
    //Maybe its 8090 instead of 8080
    return this._http.post<any>("http://localhost:8080/api/v1/login", user);
  }

  registerUserFromRemote(user: User): Observable<Object> {
    //Maybe its 8090 instead of 8080
    console.log(user);
    return this._http.post("http://localhost:8080/api/v1/register", user);
  }

  getUserByEmail(emailid:string):Observable<User>{
    return this._http.get<User>(`${this.getEmailURL}/${emailid}`);
  }
  
  getUserDetails() {
    return this._http.get("http://localhost:8080/api/v1/profile");
  }
}
