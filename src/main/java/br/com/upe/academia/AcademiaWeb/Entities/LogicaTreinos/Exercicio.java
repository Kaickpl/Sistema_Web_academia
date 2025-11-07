package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
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
@AllArgsConstructor
@NoArgsConstructor
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;
    private Duration tempoDeDescanso;

    public Exercicio (Exercicio exercicioOriginal){
        this.idExercicio = null;
        this.nomeExercicio = exercicioOriginal.getNomeExercicio();
        this.descricaoExercicio = exercicioOriginal.getDescricaoExercicio();
        this.tempoDeDescanso = exercicioOriginal.getTempoDeDescanso();
        this.series = new ArrayList<>();
        this.treinos = new ArrayList<>();
    }

    @OneToMany(mappedBy = "exercicio", cascade = CascadeType.ALL)
    private List<Serie> series;

    @ManyToMany(mappedBy = "exercicios")
    private List<Treino> treinos = new ArrayList<>();

}
