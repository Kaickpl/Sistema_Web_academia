package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conquistas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConquistas;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    private Date dataConquista;
    private String objetivo;
}
