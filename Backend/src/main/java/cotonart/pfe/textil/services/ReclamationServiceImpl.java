package cotonart.pfe.textil.services;


import cotonart.pfe.textil.entities.Reclamation;
import cotonart.pfe.textil.entities.Utilisateur;
import cotonart.pfe.textil.exception.NotFoundException;
import cotonart.pfe.textil.repositories.ReclamationRepository;
import cotonart.pfe.textil.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReclamationServiceImpl implements IReclamationservice {

    private final ReclamationRepository reclamationRepository ;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Reclamation save(Reclamation reclamation, Integer clientId) {
        Utilisateur utilisateur = utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        reclamation.setDateEnvoi(LocalDateTime.now());
        reclamation.setStatut("En attente");
        reclamation.setUtilisateur(utilisateur);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation update(Integer id, Reclamation dto) throws NotFoundException {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Rendezz-vous not found by id " + id));
        reclamation.setMessage(dto.getMessage());
        reclamation.setObjet(dto.getObjet());

        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation findById(Integer id) throws NotFoundException {
        return reclamationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Rendezz-vous not found by id " + id));
    }

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        reclamationRepository.deleteById(id);
    }


    @Override
    public List<Reclamation> getReclamationByClientId(Integer clientId) {
        return reclamationRepository.findByUtilisateurId(clientId);
    }

    @Override
    public List<Reclamation> getReclamationByCommercialId(Integer commercialId) {
        return null ;
    }

    @Override
    public Reclamation  updateStatusCommercial(Integer reclamationId, String status) {
        Reclamation reclamation = this.reclamationRepository.findById(reclamationId)
                .orElseThrow();
        reclamation.setStatut(status);
        return reclamationRepository.save(reclamation);
    }

    public List<Reclamation> getReclamationByClientIdAndCommercialId(Integer clientId, Integer commercialId) {
        return null;
    }



}
