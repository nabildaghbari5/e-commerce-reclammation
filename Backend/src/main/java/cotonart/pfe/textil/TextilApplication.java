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
			createUserIfNotExists(utilisateurRepository, passwordEncoder,
					"wissem", "wissem@gmail.com", "123", Role.ADMIN);

			createUserIfNotExists(utilisateurRepository, passwordEncoder,
					"Ahmed", "ahmed@gmail.com", "123", Role.TECHNICIEN);
		};
	}

	private void createUserIfNotExists(UtilisateurRepository repository,
									   PasswordEncoder encoder,
									   String username,
									   String email,
									   String rawPassword,
									   Role role) {
		if (repository.findByUsername(username).isEmpty()) {
			Utilisateur user = new Utilisateur();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(encoder.encode(rawPassword)); // Remplacer par un mot de passe sécurisé en production
			user.setRole(role);
			repository.save(user);
			System.out.println("Utilisateur " + role.name() + " créé : " + username);
		} else {
			System.out.println("Utilisateur " + role.name() + " existe déjà : " + username);
		}
	}


}
