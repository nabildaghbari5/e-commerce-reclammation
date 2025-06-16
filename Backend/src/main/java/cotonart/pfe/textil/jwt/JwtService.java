package cotonart.pfe.textil.jwt;

import cotonart.pfe.textil.entities.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final String KEY_FILE_PATH = "jwt-secret.key"; // Fichier pour stocker la clé
    private Key secretKey;
    private static final long EXPIRATION_TIME = 86400000; // 1 jour

    @PostConstruct
    public void init() throws IOException {
        File keyFile = new File(KEY_FILE_PATH);
        if (keyFile.exists()) {
            // Lire la clé existante
            String encodedKey = new String(Files.readAllBytes(Paths.get(KEY_FILE_PATH)));
            secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodedKey));
        } else {
            // Générer une nouvelle clé et la sauvegarder
            secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            Files.write(Paths.get(KEY_FILE_PATH), encodedKey.getBytes());
        }
    }

    public String generateToken(String username , Integer userId , Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
}
}
