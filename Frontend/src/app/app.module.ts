import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule,  HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbNavModule, NgbAccordionModule, NgbTooltipModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { ScrollToModule } from '@nicky-lenaers/ngx-scroll-to';
import { LayoutsModule } from './layouts/layouts.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ToastrModule } from 'ngx-toastr';
import { HttpInterceptorService } from './account/auth/interceptor/http-interceptor.service';
import { HomeComponent } from './compoents/home/home.component';
import { PanierComponent } from './compoents/panier/panier.component';
import { AccueilComponent } from './compoents/accueil/accueil.component';
import { FormsModule } from '@angular/forms';

export function createTranslateLoader(http: HttpClient): any {
  return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PanierComponent,
    AccueilComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,          // durée d'affichage en ms
      positionClass: 'toast-bottom-right', // position du toast
      preventDuplicates: true, // empêche les toasts dupliqués
      closeButton: true,       // bouton pour fermer
      progressBar: true        // barre de progression
    }),
    // Autres modules
 
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,  
        deps: [HttpClient]
      }
    }),
    LayoutsModule,
    AppRoutingModule,
    CarouselModule,
    NgbAccordionModule,
    NgbNavModule,
    NgbTooltipModule,
    ScrollToModule.forRoot(),  
    NgbModule, 

 
  ],
  bootstrap: [AppComponent],
  providers: [{
    provide:HTTP_INTERCEPTORS,
    useClass:HttpInterceptorService,  
    multi:true    
  },

   
   
  ],
})
export class AppModule { }
