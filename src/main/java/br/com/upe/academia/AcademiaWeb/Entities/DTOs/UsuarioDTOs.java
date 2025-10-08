package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOs {
    private UUID idUsuario;
    private String nomeUsuario;
    private String dataNascimento;
    private String senha;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public UsuarioDTOs(Usuario usuario){
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.senha = usuario.getSenha();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.tipo = usuario.getTipo();
    }


}
