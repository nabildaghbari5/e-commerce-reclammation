package cotonart.pfe.textil.controllers;


import cotonart.pfe.textil.entities.Reclamation;
import cotonart.pfe.textil.exception.NotFoundException;
import cotonart.pfe.textil.services.IReclamationservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamation")
@RequiredArgsConstructor
public class ReclmationController {

    private final IReclamationservice reclamationservice ;
    @PostMapping("/{clientId}")
    public Reclamation create(@PathVariable Integer clientId , @RequestBody Reclamation reclamation ){
        System.out.println(clientId);
        return reclamationservice.save(reclamation , clientId);
    }
    @PutMapping("/{id}")
    public Reclamation update(@PathVariable Integer id, @RequestBody Reclamation reclamation)
            throws NotFoundException {
        return  reclamationservice.update(id, reclamation);
    }

    @PutMapping("/updateStatus/{reclamationId}/{status}")
    public Reclamation updateStatusCommercial(@PathVariable Integer reclamationId, @PathVariable String status)
            throws NotFoundException {
        return  reclamationservice.updateStatusCommercial(reclamationId , status);
    }

    @GetMapping("/{id}")
    public Reclamation findById(@PathVariable Integer id) throws NotFoundException {
        return reclamationservice.findById(id);
    }
    @GetMapping("")
    public List<Reclamation> findAll() {
        return reclamationservice.findAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws NotFoundException {

        reclamationservice.delete(id);
    }

    // Endpoint pour récupérer les rendez-vous par clientId
    @GetMapping("/client/{clientId}")
    public List<Reclamation> getReclamationByClientId(@PathVariable Integer clientId) {
        return reclamationservice.getReclamationByClientId(clientId);
    }
    // Endpoint pour récupérer les rendez-vous par commercialId
    @GetMapping("/commercial/{commercialId}")
    public List<Reclamation> getReclamationByCommercialId(@PathVariable Integer commercialId) {
        return reclamationservice.getReclamationByCommercialId(commercialId);
    }

}

