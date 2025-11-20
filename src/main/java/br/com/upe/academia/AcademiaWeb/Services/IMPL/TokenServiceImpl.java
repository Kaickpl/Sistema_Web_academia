package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import br.com.upe.academia.AcademiaWeb.Services.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.token.secret}")
    String my_Secret;

    @Override
    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(my_Secret);

            return JWT.create()
                    .withIssuer("Academia")
                    .withSubject(userDetails.getUsername()) // username = email
                    .withExpiresAt(
                            LocalDateTime.now()
                                    .plusHours(4)
                                    .toInstant(ZoneOffset.of("-03:00"))
                    )
                    .sign(algorithm);

        } catch (Exception e) {
            return "";
        }

    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(my_Secret);
            return JWT.require(algorithm)
                    .withIssuer("Academia")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(Exception e){
            return "";
        }
    }
}
