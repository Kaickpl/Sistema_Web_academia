package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public Usuario buscarUsuarioPorEmail(String email);

}
