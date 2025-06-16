package cotonart.pfe.textil.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProduitDTO {

    private Integer id;
    private String nom;
    private String description;
    private Float prix;
    private Integer quantite;
    private byte[] image;
    private Integer categorieId;

}
