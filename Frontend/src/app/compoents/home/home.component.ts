import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategorieService } from 'src/app/pages/products/service/categorie.service';
import { ProduitService } from 'src/app/pages/products/service/produit.service';
import { PanierService } from '../services/panier.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  categorieList: any[] = [];
  produitList: any[] = [];

  constructor( 
    private categorieService:CategorieService,
    private produitService: ProduitService,
    private router: Router, // 👈 à injecter pour la redirection
    private panierService: PanierService
  ) { }

  ngOnInit(): void {
    this.loadCategories();
    this.loadProduits();
  }

   loadCategories(): void {
    this.categorieService.findAll().subscribe({
      next: (data) =>{
       this.categorieList = data,
        console.log(data)
      } ,
      error: (err) => console.error('Erreur lors du chargement', err),
    });
  }


  loadProduits() {
    this.produitService.getAllProduits().subscribe({
      next: (data) => {
        this.produitList = data;
        this.produitList.forEach((p: any) => {
          p.image = this.decodeBase64(p.image);
        });
      },
      error: (err) => console.error('Erreur lors du chargement', err),
    });
  }

  decodeBase64(base64String: string): string {
    return `data:image/jpeg;base64,${base64String}`;
  }

 ajouterAuPanier(produit: any): void {
  const token = localStorage.getItem('token');
  if (token) {
    this.panierService.ajouterProduit(produit);
  } else {
    this.router.navigate(['/account/signup']);
  }
}

}
