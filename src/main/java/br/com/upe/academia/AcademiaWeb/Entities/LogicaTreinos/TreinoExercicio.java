package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.utils.DurationCustomConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreinoExercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTreinoExercicio;

    @Convert(converter = DurationCustomConverter.class)
    @Column(columnDefinition = "VARCHAR(8)", nullable = false)
    private Duration tempoDeDescanso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_template_id", nullable = false)
    private Exercicio exercicioTemplate;

    @OneToMany(mappedBy = "treinoExercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Serie> seriesTemplate = new ArrayList<>();

}
