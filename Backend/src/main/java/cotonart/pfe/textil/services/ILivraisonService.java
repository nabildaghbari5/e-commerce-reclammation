package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Livraison;

import java.util.List;

public interface ILivraisonService {
    public List<Livraison> getAllLivraison();
    public Livraison save(Livraison livraison);
    public Livraison findById(Integer id);
    public void delete(Integer id);
    public Livraison update(Integer id, Livraison livraison);
}
