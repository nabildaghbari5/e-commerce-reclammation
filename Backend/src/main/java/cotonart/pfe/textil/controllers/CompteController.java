package cotonart.pfe.textil.controllers;


import cotonart.pfe.textil.entities.Compte;
import cotonart.pfe.textil.services.ICompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/compte")

public class CompteController {

    @Autowired
    ICompteService iCompteService;

    @GetMapping("/retrieve")
    public List<Compte> getAllCompte(){
        return iCompteService.getAllCompte();
    }

    @PostMapping("/save")
    public Compte save(@RequestBody Compte compte){
        return iCompteService.save(compte);
    }

    @GetMapping("/findById/{id}")
    public Compte findById(@PathVariable Integer id){
        return iCompteService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id , @RequestBody Compte compte){
         iCompteService.update(id,compte);
    }
}
