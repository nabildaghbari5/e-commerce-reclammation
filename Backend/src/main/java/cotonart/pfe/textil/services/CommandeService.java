package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.*;
import cotonart.pfe.textil.file.FileStorageService;
import cotonart.pfe.textil.file.FileUtils;
import cotonart.pfe.textil.repositories.CommandeRepository;
import cotonart.pfe.textil.repositories.ProduitRepository;
import cotonart.pfe.textil.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService implements ICommandeService {

    @Autowired
    CommandeRepository commandeRepo;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ProduitRepository produitRepository;




    @Override
    public List<Commande> getAllCommande() {
        return commandeRepo.findAll();
    }

    @Override
    public Commande save(Commande commande) {
        // Récupérer l'utilisateur par son ID
        Utilisateur utilisateur = utilisateurRepository.findById(commande.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Récupérer les produits par leurs IDs
        List<Produit> produits = commande.getProduits().stream()
                .map(p -> produitRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Produit non trouvé: " + p.getId())))
                .collect(Collectors.toList());

        // Associer les entités à la commande
        commande.setDate(LocalDate.now());
        commande.setUtilisateur(utilisateur);
        commande.setProduits(produits);

        // Enregistrer la commande
        return commandeRepo.save(commande);
    }


    @Override
    public CommandeDTO findById(Integer id) {
        Commande commande = commandeRepo.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        return mapToDto(commande);
    }

    private CommandeDTO mapToDto(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setDate(commande.getDate());
        dto.setTotal(commande.getTotal());
        dto.setDescription(commande.getDescription());
        dto.setEtat(commande.getEtat());

        // Mapper utilisateur si vous avez un DTO
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(commande.getUtilisateur().getId());
        utilisateurDTO.setUsername(commande.getUtilisateur().getUsername());
        dto.setUtilisateur(utilisateurDTO);

        // Mapper les produits avec image base64
        dto.setProduits(
                commande.getProduits().stream()
                        .map(this::mapProduitToDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    private ProduitDTO mapProduitToDto(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setNom(produit.getNom());
        produitDTO.setDescription(produit.getDescription());
        produitDTO.setPrix(produit.getPrix());
        produitDTO.setQuantite(produit.getQuantite());
        produitDTO.setCategorieId(produit.getCategorie().getId());
        produitDTO.setImage(FileUtils.readFileFromLocation(produit.getImage())); // base64
        return produitDTO;
    }

    @Override
    public void delete(Integer id){
        commandeRepo.deleteById(id);
    }

    @Override
    public Commande update(Integer id, Commande commande){
        Commande comd = commandeRepo.findById(id).get();
        if(comd != null){
            comd.setDate(LocalDate.now());
            comd.setTotal(commande.getTotal());
            comd.setDescription(commande.getDescription());
            comd.setEtat(commande.getEtat());
            return commandeRepo.save(comd);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Commande> findByUtilisateurId(Integer utilisateurId) {
        return commandeRepo.findByUtilisateurId(utilisateurId);
    }
}
