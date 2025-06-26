import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReclamationRoutingModule } from './reclamation-routing.module';
import { ReclamationAdminComponent } from './reclamation-admin/reclamation-admin.component';
import { ReclamationClientComponent } from './reclamation-client/reclamation-client.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ReclamationTechnicienComponent } from './reclamation-technicien/reclamation-technicien.component';


@NgModule({
  declarations: [
    ReclamationClientComponent,
    ReclamationAdminComponent,
    ReclamationTechnicienComponent
  ],
  imports: [
    CommonModule,
    ReclamationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbDropdownModule,
  ]
})
export class ReclamationModule { }
