package cotonart.pfe.textil.controllers;


import cotonart.pfe.textil.entities.Livraison;
import cotonart.pfe.textil.services.ILivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/livraison")
public class LivraisonController {

    @Autowired
    ILivraisonService iLivraisonService;

    @GetMapping("/retrieve")
    public List<Livraison> getAllLivraison(){
        return iLivraisonService.getAllLivraison();
    }

    @PostMapping("/save")
    public Livraison save(@RequestBody Livraison livraison){
        return iLivraisonService.save(livraison);
    }

    @GetMapping("/findById/{id}")
    public Livraison findById(@PathVariable Integer id){
        return iLivraisonService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        iLivraisonService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Livraison update(@PathVariable Integer id, @RequestBody Livraison livraison){
        Livraison liv=iLivraisonService.update(id, livraison);
        return liv;
    }
}
