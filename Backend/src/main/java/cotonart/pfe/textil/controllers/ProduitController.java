package cotonart.pfe.textil.controllers;

import cotonart.pfe.textil.entities.Produit;
import cotonart.pfe.textil.entities.ProduitDTO;
import cotonart.pfe.textil.services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/produit")
public class ProduitController {
    @Autowired
    IProduitService iProduitService;
    @GetMapping("/retrieve")
    public List<ProduitDTO> getAllProduit() {
        return iProduitService.getAllProduit();
}
    @PostMapping(value = "/save", consumes = "multipart/form-data")
    public Produit save(
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart ProduitDTO produitDTO
        ){
        return iProduitService.save(produitDTO , image);
    }
@GetMapping("/findById/{id}")
    public Produit findById(@PathVariable Integer id){
        return iProduitService.findById(id);
}
@DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        iProduitService.delete(id);
}
    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public Produit update(
            @PathVariable Integer id,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart ProduitDTO produitDTO
    ) {
        return iProduitService.update(id, produitDTO, image);
    }
}
