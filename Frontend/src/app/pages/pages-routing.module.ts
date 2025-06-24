import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [  
   
 
  { path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },
  { path: 'profils', loadChildren: () => import('./profils/profils.module').then(m => m.ProfilsModule) }, 
  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) }, 
  { path: 'commandes', loadChildren: () => import('./commandes/commandes.module').then(m => m.CommandesModule) }, 
  { path: 'reclamation', loadChildren: () => import('./reclamation/reclamation.module').then(m => m.ReclamationModule) }, 




];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
