package cotonart.pfe.textil.services;




import cotonart.pfe.textil.entities.Reclamation;
import cotonart.pfe.textil.exception.NotFoundException;

import java.util.List;

public interface IReclamationservice {


    Reclamation update(Integer id, Reclamation  dto) throws NotFoundException;
    Reclamation findById(Integer id) throws NotFoundException;
    List<Reclamation> findAll();
    void delete(Integer id) throws NotFoundException;

    Reclamation save(Reclamation reclamation, Integer clientId);

    List<Reclamation> getReclamationByClientId(Integer clientId);

    List<Reclamation> getReclamationByCommercialId(Integer commercialId);

    Reclamation updateStatusCommercial(Integer reclamationId, String status);
}
