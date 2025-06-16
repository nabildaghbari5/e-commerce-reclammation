import { UserService } from './user.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationResponse } from '../models/authentication-response';
import { AuthenticationRequest } from '../models/authentication-request';
import { RegisterRequest } from '../models/register-request';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private baseUrl = 'http://localhost:8085/api/auth'

  constructor(private http:HttpClient ) { }


   register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post<AuthenticationResponse>
    (`${this.baseUrl}/signup`, registerRequest);
  }

  login(
    authRequest: any
  ) {
    return this.http.post<any>
    (`${this.baseUrl}/signin`, authRequest);
  }


  getCurrentUserId(): string | null {
    const userString = localStorage.getItem('UserConnected'); // on suppose que l'objet est stocké sous la clé 'user'
    if (userString) {
      const user = JSON.parse(userString);
      return user.id || null;
    }
    return null;
  }
  

}
