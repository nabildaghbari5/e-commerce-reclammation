import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LayoutComponent } from './layouts/layout.component';
import { AuthGuard } from './account/auth/guards/auth.guard';
import { HomeComponent } from './compoents/home/home.component';
import { PanierComponent } from './compoents/panier/panier.component';
import { AccueilComponent } from './compoents/accueil/accueil.component';

const routes: Routes = [
   {
    path:'',
    component:AccueilComponent
  },
  {
    path:'nos-produits',
    component:HomeComponent
  },
  {
    path:'panier',
    component:PanierComponent
  },
  { path: 'account', loadChildren: () => import('./account/account.module').then(m => m.AccountModule) },
  { path: '', component: LayoutComponent, loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule)  , canActivate: [AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'top', relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})

export class AppRoutingModule { }
