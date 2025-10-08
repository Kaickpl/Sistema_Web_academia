package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
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
    private Duration tempoDeDescanso;

    @OneToMany(mappedBy = "exercicio", cascade = CascadeType.ALL)
    private List<Serie> series;

    private boolean isConcluido() {
        if(this.series == null || series.isEmpty()) {return false;}
        return this.series.stream().allMatch(Serie::isConcluida);
    }

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

}
