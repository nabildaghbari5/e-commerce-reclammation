package cotonart.pfe.textil.repositories;

import cotonart.pfe.textil.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
}
