package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    public String generateToken(UserDetails userDetails);

    public String validateToken(String token);

}
