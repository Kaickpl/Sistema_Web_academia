package br.com.upe.academia.AcademiaWeb.Entities;

import br.com.upe.academia.AcademiaWeb.Entities.Enums.perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IDcliente;
    private String cpf;
    private String nome;
    private String nascimento;
    private String email;
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdEndereco")
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private perfil perfil;
}

