package cotonart.pfe.textil.entities;

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

public class Compte  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompte")

    private Integer id;
    private String username;
    private String email;
    private String mot_de_passe;

    @OneToOne(cascade =CascadeType.ALL, mappedBy = "compte")
    private Utilisateur utilisateur;




}
