import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private baseUrl = 'http://localhost:8085/api/categorie';

  constructor(private http: HttpClient) {}

  create(categorie: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/save`, categorie);
  }

  update(id: number, categorie: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, categorie);
  }

  findById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/findById/${id}`);
  }

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/retrieve`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }
}
