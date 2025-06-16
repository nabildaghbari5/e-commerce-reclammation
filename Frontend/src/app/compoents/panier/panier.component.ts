import { Component, OnInit } from '@angular/core';
import { PanierService } from '../services/panier.service';
import { ToastrService } from 'ngx-toastr';
import { CommandeService } from '../services/commande.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.scss']  
})
export class PanierComponent implements OnInit {
  panier: any[] = [];
  total: number = 0;

  constructor(
    private panierService: PanierService,
    private commandeService: CommandeService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.chargerPanier();
  }

  chargerPanier(): void {
    this.panier = this.panierService.getPanier();
    this.total = this.panierService.getTotal();
  }

  supprimerProduit(index: number): void {
    this.panierService.supprimerProduit(index);
    this.chargerPanier();
  }

  viderPanier(): void {
    this.panierService.viderPanier();
    this.chargerPanier();
  }

  passerCommande(): void {
  const userString = localStorage.getItem('UserConnected');
  if (!userString) {
    return;
  }

  const user = JSON.parse(userString);
  const userId = user.id;

  if (this.panier.length === 0) {
    return;
  }

  const commande = {
    total: this.total,
    description: 'Commande depuis le site',
    etat: 'EN_ATTENTE',
    utilisateur: { id: userId },
    produits: this.panier.map(p => ({ id: p.id }))
  }; 

  this.commandeService.passerCommande(commande).subscribe({
    next: (res) => {
      this.panierService.viderPanier();
      this.chargerPanier();
      this.router.navigate(['/']); 

    },
    error: (err) => {
      console.error(err);
    }
  });
}


}
