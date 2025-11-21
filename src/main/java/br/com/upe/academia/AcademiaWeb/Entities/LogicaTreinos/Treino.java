package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTreino;
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Treino_Exercicio",
            joinColumns = @JoinColumn(name = "Treino_id") ,
            inverseJoinColumns = @JoinColumn(name = "Exercicio_id")
    )
    private List<Exercicio> exercicios = new ArrayList<>();

    @OneToMany(mappedBy = "treinoTemplate" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TreinoSessao> treinoExecucoes;

    }
