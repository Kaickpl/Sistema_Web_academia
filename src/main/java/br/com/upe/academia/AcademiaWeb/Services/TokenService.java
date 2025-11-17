package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Usuario;

public interface TokenService {
    public String generateToken(Usuario usuario);

    public String validateToken(String token);

}
