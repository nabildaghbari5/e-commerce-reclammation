import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategorieService } from '../service/categorie.service';
import { ProduitService } from '../service/produit.service';

@Component({
  selector: 'app-produit-list',
  templateUrl: './produit-list.component.html',
  styleUrls: ['./produit-list.component.scss']
})
export class ProduitListComponent implements OnInit {
  produitList: any[] = [];
  categorieList: any[] = [];
  produitForm!: FormGroup;
  isEditMode = false;
  modalRef!: NgbModalRef;
  selectedProduitId!: number | null;
  selectedFile: any;

  constructor(
    private fb: FormBuilder,
     private modalService: NgbModal ,   
     private categorieService:CategorieService,
     private produitService: ProduitService

  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadProduits();
    this.loadCategories();
  }

  initForm() {
    this.produitForm = this.fb.group({
      nom: [''],
      description: [''],
      prix: [0],
      quantite: [0],
      categorieId: [null],
    });
    
  }

  loadProduits() {
    this.produitService.getAllProduits().subscribe({
      next:(data)=>{
        this.produitList=data;   
      },
      error: (err) => console.error('Erreur lors du chargement', err),
    })
  }

  loadCategories() {
      this.categorieService.findAll().subscribe({
      next: (data) =>{
       this.categorieList = data,
        console.log(data)
      } , 
      error: (err) => console.error('Erreur lors du chargement', err),
    });
   
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }
  


  closeModal() {
    if (this.modalRef) {
      this.modalRef.close();
    }
    this.produitForm.reset();
  }

 openModal(content: any, produit: any = null) {
  this.isEditMode = !!produit;
  this.selectedProduitId = produit?.id || null;

  if (this.isEditMode) {
    this.produitForm.patchValue({
      nom: produit.nom,
      description: produit.description,
      prix: produit.prix,
      quantite: produit.quantite,
      categorieId: produit.categorie?.id
    });
  } else {
    this.produitForm.reset();
    this.selectedFile = null;
  }

  this.modalRef = this.modalService.open(content, { centered: true, size: 'lg' });
}

saveProduit() {
  const produitData = this.produitForm.value;

  if (!this.selectedFile && !this.isEditMode) {
    alert("Veuillez sélectionner une image.");
    return;
  }

  if (this.isEditMode && this.selectedProduitId) {
    // ✅ Mode édition : appeler updateProduit()
    this.produitService.updateProduit(this.selectedProduitId, produitData, this.selectedFile).subscribe({
      next: () => {
        console.log('Produit mis à jour avec succès');
        this.closeModal();
        this.loadProduits();
      },
      error: (err) => {
        console.error("Erreur lors de la mise à jour du produit", err);
      }
    });
  } else {
    // ✅ Mode ajout : appeler saveProduit()
    this.produitService.saveProduit(produitData, this.selectedFile!).subscribe({
      next: () => {
        console.log('Produit enregistré avec succès');
        this.closeModal();
        this.loadProduits();
      },
      error: (err) => {
        console.error("Erreur lors de l’enregistrement du produit", err);
      }
    });
  }
}


  onDelete(id: number) {
   this.produitService.deleteProduit(id).subscribe({
      next: () => this.loadProduits(),
      error: (err) => console.error('Erreur lors de la suppression', err),
    });
  }




}
