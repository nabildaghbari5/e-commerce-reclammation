import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent {
  
    
  services = [
    {
      icon: 'shield',
      title: 'Protection Électronique',
      description: 'Systèmes de protection contre le vol et l\'incendie avec les dernières technologies'
    },
    {
      icon: 'video',
      title: 'Vidéosurveillance',
      description: 'Solutions de surveillance vidéo avancées pour sécuriser vos locaux'
    },
    {
      icon: 'key',
      title: 'Contrôle d\'Accès',
      description: 'Systèmes de contrôle d\'accès intelligents et sécurisés'
    },
    {
      icon: 'door',
      title: 'Automatisation des Portes',
      description: 'Automatisation professionnelle pour portes et accès'
    }
  ];

  clients = [
    {
      category: 'Immobilier',
      companies: ['IMMOBILIER ESAYEDI', 'IMMOBILIER ROUATBI', 'IMMOBILIER BEN HASSIN']
    },
    {
      category: 'Industrie',
      companies: ['LEONI Mater', 'Fawanis', 'Euro confection', 'Demco', 'Rotana Cosmetics', 'SCD']
    },
    {
      category: 'Hôtellerie',
      companies: ['HÔTEL MÖVENPICK RESORT & MARINE SPA', 'HOTEL DAR JERBA', 'HOTEL MARABOUT', 'HOTEL TOUR KHALEF', 'HOTEL LES ORANGES']
    }
  ];

  constructor( private router:Router) { }

  produitList(){
    this.router.navigate(['/nos-produits'])
  }

  scrollToSection(sectionId: string) {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
