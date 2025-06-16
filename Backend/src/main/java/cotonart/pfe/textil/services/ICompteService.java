package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Compte;

import java.util.List;

public interface ICompteService {
    public List<Compte> getAllCompte();
    public Compte save(Compte compte);
    public Compte findById(Integer id);
    public void delete(Integer id);
    public Compte update(Integer id, Compte compte);
}
