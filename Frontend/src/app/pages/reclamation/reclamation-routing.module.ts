import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReclamationAdminComponent } from './reclamation-admin/reclamation-admin.component';
import { ReclamationClientComponent } from './reclamation-client/reclamation-client.component';
import { ReclamationTechnicienComponent } from './reclamation-technicien/reclamation-technicien.component';

const routes: Routes = [
  {
    path:"reclamation-client",
    component:ReclamationClientComponent
  },
  {
    path:"reclamation-admin",
    component:ReclamationAdminComponent
  },
  {
    path:"reclamation-technicien",
    component:ReclamationTechnicienComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReclamationRoutingModule { }
