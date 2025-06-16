package cotonart.pfe.textil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Entity

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class Message implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idMessage")

    private Integer id;
    public String message;
    private Date date;
     /*
    @ManyToOne
    private Utilisateur utilisateur;*/



}
