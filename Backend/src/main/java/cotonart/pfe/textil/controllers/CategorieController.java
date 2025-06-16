package cotonart.pfe.textil.controllers;

import cotonart.pfe.textil.entities.Categorie;
import cotonart.pfe.textil.services.ICategorieSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categorie")

public class CategorieController {

    @Autowired
    ICategorieSevice iCategorieSevice;

    @GetMapping("/retrieve")
    public List<Categorie> getAllCategorie(){
        return iCategorieSevice.getAllCategorie();
    }

    @PostMapping("/save")
    public Categorie save(@RequestBody Categorie categorie){
        return iCategorieSevice.save(categorie);

    }
    @GetMapping("/findById/{id}")
    public Categorie findById(@PathVariable Integer id){
        return iCategorieSevice.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        iCategorieSevice.delete(id);
    }

    @PutMapping("/update/{id}")
    public Categorie update(@PathVariable Integer id, @RequestBody Categorie categorie){
        Categorie cat = iCategorieSevice.update(id , categorie);
        return cat;
    }

}
