import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitListComponent } from './produit-list/produit-list.component';
import { CategorieComponent } from './categorie/categorie.component';

const routes: Routes = [
  {
    path:'list',
    component:ProduitListComponent
  },
  {
    path:'catégorie',
    component:CategorieComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
