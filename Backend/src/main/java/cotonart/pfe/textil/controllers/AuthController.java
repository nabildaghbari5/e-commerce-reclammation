package cotonart.pfe.textil.controllers;

import cotonart.pfe.textil.entities.Utilisateur;
import cotonart.pfe.textil.jwt.JwtService;
import cotonart.pfe.textil.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    UtilisateurService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Utilisateur utilisateur) {
        Utilisateur savedUser = userService.signup(utilisateur);
        String token = jwtService.generateToken(utilisateur.getUsername() , utilisateur.getId() , utilisateur.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("username", savedUser.getUsername());
        response.put("email", savedUser.getEmail());
        response.put("token", token);
        response.put("role" , savedUser.getRole() );
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody Utilisateur utilisateur) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword())
        );
        Utilisateur authenticatedUser = (Utilisateur) authentication.getPrincipal();
        String token = jwtService.generateToken(utilisateur.getUsername() , utilisateur.getId() , utilisateur.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("id", authenticatedUser.getId());
        response.put("username", authenticatedUser.getUsername());
        response.put("email", authenticatedUser.getEmail());
        response.put("token", token);
        response.put("role" , authenticatedUser.getRole() );
        return ResponseEntity.ok(response);
    }


}
