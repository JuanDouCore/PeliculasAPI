package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.service.JwtService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-minutes}")
    private long tokenExpirationMinutes;

    @Override
    public String generarToken(Usuario usuario) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (tokenExpirationMinutes * 60 * 1000));

        return Jwts.builder()
                .setClaims(generateExtraClaims(usuario))
                .setSubject(usuario.getCorreo())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public void validarToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token);
    }

    @Override
    public String obtenerEmailDelToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Map<String, Object> generateExtraClaims(Usuario user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("correo", user.getUsername());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }

    private Key generateKey() {
        byte[] secretAsBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(secretAsBytes);
    }
}
