package cotonart.pfe.textil.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProduit")
    private Integer id;
    private String nom;
    private String description;
    private Float prix;
    private Integer quantite;
    private String image;


    @ManyToMany( mappedBy = "produits")
    @JsonIgnore
    private List<Commande> commandes;

    @ManyToOne
    private Categorie categorie;

}



