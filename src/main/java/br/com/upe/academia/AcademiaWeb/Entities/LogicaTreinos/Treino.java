package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTreino;
    private Duration duracao;
    private String nome;
    private boolean isConcluido = false;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL)
    private List<Exercicio> exercicio;

    }
