package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class ExercicioSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idExercicioSessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private Treino treino;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_sessao_id")
    private List<SerieSessao> serieSessao;

}
