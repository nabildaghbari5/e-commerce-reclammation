import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommandesRoutingModule } from './commandes-routing.module';
import { CommandeAdminComponent } from './commande-admin/commande-admin.component';
import { CommandeClientComponent } from './commande-client/commande-client.component';


@NgModule({
  declarations: [
    CommandeAdminComponent,
    CommandeClientComponent
  ],
  imports: [
    CommonModule,
    CommandesRoutingModule
  ]  
})
export class CommandesModule { }
