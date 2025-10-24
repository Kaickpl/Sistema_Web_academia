package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
public class TreinoSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant dataExecucao = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_treino_id", nullable = false)
    private Treino treinoTemplate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ExercicioSessao")
    private List<ExercicioSessao> exercicioSessao;

}
