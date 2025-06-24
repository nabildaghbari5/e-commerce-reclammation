import { Subscription } from 'rxjs';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuthentificationService } from 'src/app/account/auth/services/authentification.service';
import { CommandeService } from 'src/app/compoents/services/commande.service';

@Component({
  selector: 'app-commande-client',
  templateUrl: './commande-client.component.html',
  styleUrls: ['./commande-client.component.scss']
})
export class CommandeClientComponent implements OnInit {
  commandeList: any[] = [];
  selectedCommande: any;
  modalRef!: NgbModalRef;
  userId:any
  constructor(
    private commandeService: CommandeService,
    private modalService: NgbModal , 
    private authentificationService:AuthentificationService
  ) {}

  ngOnInit(): void {  
    this.userId = this.authentificationService.getCurrentUserId();
    if(this.userId){
      this.loadCommandes()
    }
  }

  loadCommandes(): void {
    this.commandeService.findCommandeByUser(this.userId).subscribe({
      next: (data) => this.commandeList = data,
      error: (err) => console.error('Erreur chargement commandes', err)
    });
  }

openDetailsModal(content: TemplateRef<any>, commande: any): void {
  const commandeId = commande.id;

  this.commandeService.findById(commandeId).subscribe({
    next: (fullCommande) => {
      // Décoder les images de tous les produits
      const produitsAvecImages = fullCommande.produits?.map((produit: any) => ({
        ...produit,
        image: this.decodeBase64(produit.image)
      }));

      this.selectedCommande = {
        ...fullCommande,
        produits: produitsAvecImages
      };

      // Ouvrir le modal après avoir chargé tous les détails
      this.modalRef = this.modalService.open(content, {
        centered: true,
        size: 'lg',
        backdrop: 'static'
      });
    },
    error: (err) => {
      console.error('Erreur lors du chargement de la commande', err);
    }
  });
}


decodeBase64(base64String: string): string {
  return `data:image/jpeg;base64,${base64String}`;
}

  closeModal(): void {
    if (this.modalRef) {
      this.modalRef.close();
    }
  }

  onDeleteCommande(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')) {
      this.commandeService.deleteCommande(id).subscribe({
        next: () => {
          this.loadCommandes();
          if (this.selectedCommande?.id === id) {
            this.closeModal();
          }
        },
        error: (err) => console.error('Erreur suppression commande', err)
      });
    }
  }

 
}
