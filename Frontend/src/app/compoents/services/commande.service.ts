import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  private baseUrl = 'http://localhost:8085/api/commande';

  constructor(private http: HttpClient) {}

  passerCommande(commande: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/save`, commande);
  }

  getAllCommandes(): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/retrieve`);
   }

  deleteCommande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

   findById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/findById/${id}`);
  }



}
