package cotonart.pfe.textil.services;


import cotonart.pfe.textil.entities.Categorie;
import cotonart.pfe.textil.entities.Produit;
import cotonart.pfe.textil.entities.ProduitDTO;
import cotonart.pfe.textil.file.FileStorageService;
import cotonart.pfe.textil.file.FileUtils;
import cotonart.pfe.textil.repositories.CategorieRepository;
import cotonart.pfe.textil.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService implements IProduitService {
    @Autowired
    ProduitRepository produitRepo;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    private  FileStorageService fileStorageService;


    @Override
    public List<ProduitDTO> getAllProduit() {
        return produitRepo.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ProduitDTO mapToDto(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setNom(produit.getNom());
        produitDTO.setDescription(produit.getDescription());
        produitDTO.setPrix(produit.getPrix());
        produitDTO.setQuantite(produit.getQuantite());
        produitDTO.setCategorieId(produit.getCategorie().getId());
        produitDTO.setImage(FileUtils.readFileFromLocation(produit.getImage()));
    return produitDTO ;
    }

    @Override
    public Produit save(ProduitDTO produitDTO, MultipartFile image) {
        Produit produit = new Produit();
        Categorie categorie =categorieRepository.findById(produitDTO.getCategorieId())
                .orElseThrow();
        String uploadedImage = null;
        if (image != null && !image.isEmpty()) {
            uploadedImage = fileStorageService.saveFile(image, "produit");
            produit.setImage(uploadedImage);
        }
        produit.setNom(produitDTO.getNom());
        produit.setDescription(produitDTO.getDescription());
        produit.setPrix(produitDTO.getPrix());
        produit.setQuantite(produitDTO.getQuantite());
        produit.setCategorie(categorie);

        return produitRepo.save(produit);
    }

    @Override
    public Produit findById(Integer id) {
        return produitRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        produitRepo.deleteById(id);
    }

    @Override
    public Produit update(Integer id, ProduitDTO produitDTO, MultipartFile image) {
        Produit produit = produitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'id: " + id));

        Categorie categorie = categorieRepository.findById(produitDTO.getCategorieId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'id: " + produitDTO.getCategorieId()));

        if (image != null && !image.isEmpty()) {
            String uploadedImage = fileStorageService.saveFile(image, "produit");
            produit.setImage(uploadedImage);
        }

        produit.setNom(produitDTO.getNom());
        produit.setDescription(produitDTO.getDescription());
        produit.setPrix(produitDTO.getPrix());
        produit.setQuantite(produitDTO.getQuantite());
        produit.setCategorie(categorie);

        return produitRepo.save(produit);
    }

}



