import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  private baseUrl = 'http://localhost:8085/api/produit';

  constructor(private http: HttpClient) {}

  // ✅ Récupérer tous les produits
  getAllProduits(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/retrieve`);
  }

  // ✅ Récupérer un produit par ID
  getProduitById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/findById/${id}`);
  }

  // ✅ Supprimer un produit
  deleteProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  updateProduit(id: number, produitDTO: any, image?: File): Observable<any> {
  const formData = new FormData();

  formData.append('produitDTO', new Blob([JSON.stringify(produitDTO)], { type: 'application/json' }));

  if (image) {
    formData.append('image', image);
  }

  return this.http.put<any>(`${this.baseUrl}/update/${id}`, formData);
}


  // ✅ Enregistrer un produit avec une image
  saveProduit(produit: any, image: File): Observable<any> {
    const formData = new FormData();

    formData.append('image', image);
    formData.append('produitDTO', new Blob([JSON.stringify(produit)], {
      type: 'application/json'
    }));

    return this.http.post<any>(`${this.baseUrl}/save`, formData);
  }
}
