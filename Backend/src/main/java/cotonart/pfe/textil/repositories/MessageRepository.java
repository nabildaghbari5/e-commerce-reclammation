package cotonart.pfe.textil.repositories;

import cotonart.pfe.textil.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
