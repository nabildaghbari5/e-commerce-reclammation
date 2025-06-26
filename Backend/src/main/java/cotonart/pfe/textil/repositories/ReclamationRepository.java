package cotonart.pfe.textil.repositories;

import cotonart.pfe.textil.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {

    List<Reclamation> findByUtilisateurId(Integer clientId);
}
