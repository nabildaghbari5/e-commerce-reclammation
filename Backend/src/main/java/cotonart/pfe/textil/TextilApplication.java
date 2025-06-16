package cotonart.pfe.textil;

import cotonart.pfe.textil.entities.Role;
import cotonart.pfe.textil.entities.Utilisateur;
import cotonart.pfe.textil.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TextilApplication {

	public static void main(String[] args) {


		SpringApplication.run(TextilApplication.class, args);

	}

	@Bean
	CommandLineRunner initAdmin(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			String adminUsername = "wissem";
			if (utilisateurRepository.findByUsername(adminUsername).isEmpty()) {
				Utilisateur admin = new Utilisateur();
				admin.setUsername(adminUsername);
				admin.setEmail("wissem@gmail.com");
				admin.setPassword(passwordEncoder.encode("123")); // à changer pour un mot de passe sécurisé
				admin.setRole(Role.ADMIN);
				utilisateurRepository.save(admin);
				System.out.println("Utilisateur ADMIN créé.");
			} else {
				System.out.println("Utilisateur ADMIN existe déjà.");
			}
		};
	}

}
