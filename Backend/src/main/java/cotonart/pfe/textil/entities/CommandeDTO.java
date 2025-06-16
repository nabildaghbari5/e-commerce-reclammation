package cotonart.pfe.textil.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CommandeDTO {
    private Integer id;
    private LocalDate date;
    private Float total;
    private String description;
    private String etat;
    private UtilisateurDTO utilisateur; // ou Integer utilisateurId si vous voulez simplifier
    private List<ProduitDTO> produits;
}

