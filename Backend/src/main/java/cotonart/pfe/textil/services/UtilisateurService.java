package cotonart.pfe.textil.services;


import cotonart.pfe.textil.entities.Utilisateur;
import cotonart.pfe.textil.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService implements IUtilisateurService, UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur){
        return utilisateurRepo.save(utilisateur);
    }

    @Override
    public Utilisateur findById(Integer id){
        return utilisateurRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id){
        utilisateurRepo.deleteById(id);
    }

    @Override
    public Utilisateur update(Integer id, Utilisateur utilisateur){
        Utilisateur utili = utilisateurRepo.findById(id).get();
        if (utili != null){
            utili.setUsername(utilisateur.getUsername());
            utili.setEmail(utili.getEmail());
            utili.setPassword(utili.getPassword());
            //utili.setRole(utilisateur.getRole());
        }
        return utilisateurRepo.save(utilisateur);
    }
    @Override
    public Utilisateur signup(Utilisateur user) {
        if (utilisateurRepo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (utilisateurRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return utilisateurRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User notfound"));
}


}
