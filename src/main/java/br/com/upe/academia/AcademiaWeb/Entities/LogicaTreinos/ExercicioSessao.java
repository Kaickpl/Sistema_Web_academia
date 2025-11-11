package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @JoinColumn(name = "treinoExecucao_id", nullable = false)
    private TreinoSessao treinoExecucao;

    @OneToMany(mappedBy = "exercicioSessao",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SerieSessao> seriesRealizadas =  new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicioTemplate_id", nullable = false)
    private Exercicio exercicioTemplate;

    public double volumeTotal(){
        double volume = 0;
        for(SerieSessao serieSessao : seriesRealizadas){
            volume += serieSessao.getPesoTotal();
        }
        return volume;
    }
}
