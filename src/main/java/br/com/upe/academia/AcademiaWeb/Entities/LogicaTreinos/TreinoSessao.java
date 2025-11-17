package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import jakarta.persistence.*;
import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreinoSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTreinoSessao;
    private String comentario;
    private Instant dataExecucao;
    private Instant tempoFinalizacao;
    private boolean concluido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_treino_id", nullable = false)
    private Treino treinoTemplate;

    @OneToMany(mappedBy = "treinoExecucao",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExercicioSessao> exerciciosRealizados = new ArrayList<>();

    public java.time.Duration getDuracaoTotal() {
        if (dataExecucao != null && tempoFinalizacao != null) {
            return java.time.Duration.between(dataExecucao, tempoFinalizacao);
        }
        return java.time.Duration.ZERO;
    }
}
