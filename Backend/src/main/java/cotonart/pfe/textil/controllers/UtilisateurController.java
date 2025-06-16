package cotonart.pfe.textil.controllers;


import cotonart.pfe.textil.entities.Message;
import cotonart.pfe.textil.entities.Utilisateur;
import cotonart.pfe.textil.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/utilisateur")

public class UtilisateurController {
    @Autowired
    IUtilisateurService iUtilisateurService;

    @GetMapping("/retrieve")
    public List<Utilisateur> getAllUtilisateur(){
        return iUtilisateurService.getAllUtilisateur();
    }

    @PostMapping("/save")
    public Utilisateur save(@RequestBody Utilisateur utilisateur){
        return iUtilisateurService.save(utilisateur);
    }

    @GetMapping("/findById/{id}")
    public Utilisateur findById(@PathVariable Integer id){
        return iUtilisateurService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        iUtilisateurService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable Integer id, @RequestBody Utilisateur utilisateur){
        Utilisateur utili = iUtilisateurService.update(id,utilisateur);
        return utili;
    }
}
