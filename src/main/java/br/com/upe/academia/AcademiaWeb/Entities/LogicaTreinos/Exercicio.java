package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.utils.DurationCustomConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;

    @Convert(converter = DurationCustomConverter.class)
    @Column(columnDefinition = "VARCHAR(8)", nullable = false, length = 8)
    private Duration tempoDeDescanso;

    @OneToMany(mappedBy = "exercicio", cascade = CascadeType.ALL)
    private List<Serie> series;

    @ManyToMany(mappedBy = "exercicios")
    private List<Treino> treinos = new ArrayList<>();

    @OneToMany(mappedBy = "exercicioTemplate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExercicioSessao> exerciciosExecucao = new ArrayList<>();

}
