import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { ProduitListComponent } from './produit-list/produit-list.component';
import { CategorieComponent } from './categorie/categorie.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductClientComponent } from './product-client/product-client.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';


@NgModule({
  declarations: [
    ProduitListComponent,
    CategorieComponent,
    ProductClientComponent,
    ProductDetailComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProductsModule { }
