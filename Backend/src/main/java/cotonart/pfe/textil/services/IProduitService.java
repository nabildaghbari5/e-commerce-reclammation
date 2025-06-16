package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Produit;
import cotonart.pfe.textil.entities.ProduitDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProduitService {
    public List<ProduitDTO> getAllProduit() ;
    public Produit save(ProduitDTO produitDTO, MultipartFile image);
    public Produit findById(Integer id);
    public void delete(Integer id);

    Produit update(Integer id, ProduitDTO produitDTO, MultipartFile image);
}
