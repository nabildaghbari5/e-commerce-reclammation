package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Produit;
import cotonart.pfe.textil.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    public List<Utilisateur> getAllUtilisateur();
    public Utilisateur save(Utilisateur utilisateur);
    public Utilisateur findById(Integer id);
    public void delete(Integer id);
    public Utilisateur update(Integer id , Utilisateur utilisateur);
    public Utilisateur signup(Utilisateur user);
}
