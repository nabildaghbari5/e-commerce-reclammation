package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Categorie;
import cotonart.pfe.textil.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements ICategorieSevice {

    @Autowired
    CategorieRepository categorieRepo;

    @Override
    public List<Categorie> getAllCategorie(){
        return categorieRepo.findAll();
    }

    @Override
    public Categorie save (Categorie categorie){
        return categorieRepo.save(categorie);
    }

    @Override
    public Categorie findById(Integer id){
        return categorieRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id){
        categorieRepo.deleteById(id);
    }

    @Override
    public Categorie update(Integer id, Categorie categorie){
        Categorie cat = categorieRepo.findById(id).get();
        if (cat != null) {
            cat.setNom(categorie.getNom());
            cat.setDescription(categorie.getDescription());
            return categorieRepo.save(cat);
        }
        else  {
            return null;
        }
    }


}
