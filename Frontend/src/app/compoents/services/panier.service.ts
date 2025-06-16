import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  private panierKey = 'panier';

  getPanier(): any[] {
    const panier = localStorage.getItem(this.panierKey);
    return panier ? JSON.parse(panier) : [];
  }

  ajouterProduit(produit: any): void {
    const panier = this.getPanier();
    panier.push(produit);
    localStorage.setItem(this.panierKey, JSON.stringify(panier));
  }

  supprimerProduit(index: number): void {
    const panier = this.getPanier();
    panier.splice(index, 1);
    localStorage.setItem(this.panierKey, JSON.stringify(panier));
  }

  viderPanier(): void {
    localStorage.removeItem(this.panierKey);
  }

  getTotal(): number {
    return this.getPanier().reduce((total, item) => total + item.prix, 0);
  }
}
