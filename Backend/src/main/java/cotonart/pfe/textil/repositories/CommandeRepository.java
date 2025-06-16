package cotonart.pfe.textil.repositories;

import cotonart.pfe.textil.entities.Commande;
import cotonart.pfe.textil.entities.Statut;
import cotonart.pfe.textil.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    List<Commande> findByUtilisateurId(Integer utilisateurId);
}
