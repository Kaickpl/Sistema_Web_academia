package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idExercicioSessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinoExecucao_id")
    private TreinoSessao treinoExecucao;

    @OneToMany(mappedBy = "exercicioSessao",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SerieSessao> seriesRealizadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicioTemplate_id", nullable = false)
    private Exercicio exercicioTemplate;

}
