import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommandeAdminComponent } from './commande-admin/commande-admin.component';
import { CommandeClientComponent } from './commande-client/commande-client.component';

const routes: Routes = [
  {
    path:'suivi-commande',
    component:CommandeAdminComponent
  },
   {
    path:'suivi-commande-client',
    component:CommandeClientComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommandesRoutingModule { }
