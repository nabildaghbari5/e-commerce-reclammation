package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Categorie;

import java.util.List;

public interface ICategorieSevice {
    public List<Categorie> getAllCategorie();
    public Categorie save(Categorie categorie);
    public Categorie findById(Integer id);
    public void delete(Integer id);
    public Categorie update(Integer id, Categorie categorie);
}
