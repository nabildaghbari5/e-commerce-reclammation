
export const MENU = {
    adminMenu: [
      

        {
            id: 1,
            label: 'Catégorie',
            link: 'products/catégorie',   

        },    
        {
            id: 2,
            label: 'Liste des produits',  
            link: 'products/list',  

        },   
        {
            id: 3,
            label: 'Suivi de commande',  
            link: 'commandes/suivi-commande',  

        },     
         {
            id: 4,
            label: 'Suivi reclamations',  
             link: 'reclamation/reclamation-admin', 

        },     
       
    ],

    
    Client: [
        {
            id: 1,
            label: "Suivi de commande",
            link: 'commandes/suivi-commande-client', 
        },
        {
            id: 2,
            label: "Réclamation",  
            link: 'reclamation/reclamation-client', 
        },


       
    ],
    Technicien: [
        {
            id: 1,
            label: "Réclamation",  
            link: 'reclamation/reclamation-technicien', 
        },


       
    ],
};
