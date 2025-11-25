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

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreinoExercicio> regrasDeExercicios =  new ArrayList<>();

    @OneToMany(mappedBy = "treinoTemplate" ,fetch = FetchType.LAZY)
    private List<TreinoSessao> treinoExecucoes =  new ArrayList<>();

    }
