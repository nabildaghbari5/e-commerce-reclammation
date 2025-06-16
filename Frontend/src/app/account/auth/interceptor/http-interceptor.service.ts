import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const registrationUrl = 'http://localhost:8085/api/auth/signup';
    const authenticationUrl = 'http://localhost:8085/api/auth/signin';

    if (req.url === registrationUrl || req.url === authenticationUrl) {
      return next.handle(req);
    }

    const token = localStorage.getItem('token');

    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(authReq);
    } else {
      console.warn('Token not found - redirecting to login');
      this.router.navigate(['/login']); // Assure-toi que le routeur est bien configuré
      return next.handle(req); // Peut être remplacé par EMPTY si tu veux annuler la requête
    }
  }
}
