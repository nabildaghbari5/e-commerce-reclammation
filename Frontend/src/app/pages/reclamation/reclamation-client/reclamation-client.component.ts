import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ReclamationService } from '../service/reclamation.service';
import { AuthentificationService } from 'src/app/account/auth/services/authentification.service';
import { UserService } from 'src/app/account/auth/services/user.service'; 

@Component({
  selector: 'app-reclamation-client',
  templateUrl: './reclamation-client.component.html',
  styleUrls: ['./reclamation-client.component.scss']
})
export class ReclamationClientComponent implements OnInit {

  reclamationList: any[] = []; 
  reclamationForm!: FormGroup;
  modalRef!: NgbModalRef;
  userId:any;
  isEditMode = false;
  currentReclamationId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private modalService: NgbModal,
    private reclamationService: ReclamationService,
    private authentificationService: AuthentificationService,
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.userId = this.authentificationService.getCurrentUserId();
    console.log(this.userId);
   if(this.userId){
      this.getReclamationsByClientId()
    }
  }

  initForm() {
    this.reclamationForm = this.fb.group({
      objet: ['', Validators.required],
      message: ['', Validators.required],
    });
  }

  openModal(content: any, reclamation?: any) {
    this.isEditMode = !!reclamation;
    if (reclamation) {
      this.currentReclamationId = reclamation.id;
      this.reclamationForm.patchValue({
        objet: reclamation.objet,
        description: reclamation.description,
      });
    } else {
      this.currentReclamationId = null;
      this.reclamationForm.reset();
    }
    this.modalRef = this.modalService.open(content, { centered: true, size: 'lg' });
  }

  closeModal() {
    if (this.modalRef) {
      this.modalRef.close();
    }
  }

  saveReclamation() {
    if (this.reclamationForm.invalid) {
      return;
    }

    const formData = {
      ...this.reclamationForm.value,
    };

    if (this.isEditMode && this.currentReclamationId) {
      this.reclamationService.update(this.currentReclamationId, formData).subscribe({
        next: () => {
          this.getReclamationsByClientId();
          this.closeModal();
        },
        error: (err) => console.error("Erreur modification", err)
      });
    } else {
      this.reclamationService.create(formData, this.userId).subscribe({
        next: () => {
          this.getReclamationsByClientId();
          this.closeModal();
        },
        error: (err) => console.error("Erreur création", err)
      });
    }
  }

  onDelete(id: number) {
    this.reclamationService.delete(id).subscribe({
      next: () => this.getReclamationsByClientId(),
      error: (err) => console.error("Erreur suppression", err)
    });
  }

  getReclamationsByClientId() {
    this.reclamationService.findByClientId(this.userId).subscribe({
      next: (data) => this.reclamationList = data,
      error: (err) => console.error("Erreur récupération", err)
    });
  }
}