package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class ExercicioSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long idExercicioSessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private Treino treino;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_sessao_id")
    private List<SerieSessao> serieSessao;

}
