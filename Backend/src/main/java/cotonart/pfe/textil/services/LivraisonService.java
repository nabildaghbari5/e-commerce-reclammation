package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Livraison;
import cotonart.pfe.textil.repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LivraisonService implements ILivraisonService{

    @Autowired
    LivraisonRepository livraisonRepo;

    @Override
    public List<Livraison> getAllLivraison(){
        return livraisonRepo.findAll();
    }

    @Override
    public Livraison save(Livraison livraison){
        return livraisonRepo.save(livraison);
    }

    @Override
    public Livraison findById(Integer id){
        return livraisonRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id){
        livraisonRepo.deleteById(id);
    }

    @Override
    public Livraison update(Integer id, Livraison livraison){
        Livraison liv = livraisonRepo.findById(id).get();
        if(liv!=null){
            liv.setAdresseLivraison(livraison.getAdresseLivraison());
            liv.setDateLivraison(livraison.getDateLivraison());
            liv.setStatut(livraison.getStatut());
        }
        return livraisonRepo.save(liv);
    }
}
