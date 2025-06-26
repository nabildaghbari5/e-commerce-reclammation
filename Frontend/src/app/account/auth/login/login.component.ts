
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthentificationService } from '../services/authentification.service';
import { AuthenticationResponse } from '../models/authentication-response';
import { VerificationRequest } from '../models/verification-request';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {
  error = '';
  returnUrl: string;
  loginForm: FormGroup;
  authResponse: any = {};
  otpCode = '';
  message = '';
  year: number = new Date().getFullYear();
  userService: UserService;
  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private authService:AuthentificationService ,  
    userService:UserService ) {
      this.userService = userService;
     }
   
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]], 
    });
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    const formData = this.loginForm.value
    console.log(formData)
    this.authService.login(formData)
      .subscribe({
        next: (response) => {
          this.authResponse = response;
            localStorage.setItem('token', response.token as string);
             localStorage.setItem('UserConnected', JSON.stringify({
            id: response.id,
            username: response.username,
            email: response.email,
            role: response.role,
             }));  

            switch (this.authResponse.role) {
                case 'ACHETEUR':  
                  this.router.navigate(['/']);  
                  break;
                case 'TECHNICIEN':  
                  this.router.navigate(['/reclamation/reclamation-technicien']); 
                  break;
                default:  
                   this.router.navigate(['/products/catégorie']); 
                  break;
              }
        },
        error: (err) => {
          console.error('Erreur lors de l\'authentification :', err.error.message);
          this.message = err.error.message;
        }
      }); 
  }




}
