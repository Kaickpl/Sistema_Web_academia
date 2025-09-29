package br.com.upe.academia.AcademiaWeb.Entities;

import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;
    private String nomeUsuario;
    private String dataNascimento;
    private String senha;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}

