import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategorieService } from '../service/categorie.service';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.scss']
})
export class CategorieComponent implements OnInit {
  categorieList: any[] = [];
  categorieForm!: FormGroup;
  modalRef!: NgbModalRef;
  isEditMode = false;
  currentCategorieId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private modalService: NgbModal,
    private categorieService: CategorieService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadCategories();
  }

  initForm(): void {
    this.categorieForm = this.fb.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  openModal(content: any, categorie?: any): void {
    this.isEditMode = !!categorie;

    if (this.isEditMode) {
      this.currentCategorieId = categorie.id;
      this.categorieForm.patchValue({
        nom: categorie.nom,
        description: categorie.description,
      });
    } else {
      this.currentCategorieId = null;
      this.categorieForm.reset();
    }

    this.modalRef = this.modalService.open(content, { centered: true, size: 'lg' });
  }

  closeModal(): void {
    this.modalRef?.close();
  }

  saveCategorie(): void {
    if (this.categorieForm.invalid) return;

    const formData = this.categorieForm.value;

    const action = this.isEditMode
      ? this.categorieService.update(this.currentCategorieId!, formData)
      : this.categorieService.create(formData);

    action.subscribe({
      next: () => {
        this.loadCategories();
        this.closeModal();
      },
      error: (err) => console.error('Erreur lors de la sauvegarde', err),
    });
  }

  onDelete(id: number): void {
    this.categorieService.delete(id).subscribe({
      next: () => this.loadCategories(),
      error: (err) => console.error('Erreur lors de la suppression', err),
    });
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
}

