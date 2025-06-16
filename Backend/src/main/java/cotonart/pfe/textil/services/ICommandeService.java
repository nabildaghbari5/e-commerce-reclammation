package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Commande;
import cotonart.pfe.textil.entities.CommandeDTO;

import java.util.List;

public interface ICommandeService {
    public List<Commande> getAllCommande();
    public Commande save(Commande commande);
    public Commande update(Integer id , Commande commande);
    public void delete(Integer id);
    public CommandeDTO findById(Integer id);
    public List<Commande> findByUtilisateurId(Integer utilisateurId);

}
