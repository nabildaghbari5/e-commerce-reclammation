package cotonart.pfe.textil.services;


import cotonart.pfe.textil.entities.Compte;
import cotonart.pfe.textil.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService implements ICompteService{

    @Autowired
    CompteRepository compteRepo;

    @Override
    public List<Compte> getAllCompte(){
        return compteRepo.findAll();
    }

    @Override
    public Compte save(Compte compte){
        return compteRepo.save(compte);
    }

    @Override
    public Compte findById(Integer id){
        return compteRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id){
        compteRepo.deleteById(id);
    }

    @Override
    public Compte update(Integer id, Compte compte){
        Compte cot = compteRepo.findById(id).get();
        if (cot != null){
            cot.setUsername(compte.getUsername());
            cot.setEmail(compte.getEmail());
            cot.setMot_de_passe(compte.getMot_de_passe());
        }
        return compteRepo.save(compte);
    }
}

