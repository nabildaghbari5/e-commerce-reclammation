import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommandesRoutingModule } from './commandes-routing.module';
import { CommandeAdminComponent } from './commande-admin/commande-admin.component';


@NgModule({
  declarations: [
    CommandeAdminComponent
  ],
  imports: [
    CommonModule,
    CommandesRoutingModule
  ]  
})
export class CommandesModule { }
