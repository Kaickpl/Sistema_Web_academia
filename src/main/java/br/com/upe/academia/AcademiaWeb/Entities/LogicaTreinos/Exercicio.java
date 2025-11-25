package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.MusculoTrabalhado;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
@NoArgsConstructor
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MusculoTrabalhado musculoPrincipal;

    @OneToMany(mappedBy ="exercicioTemplate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TreinoExercicio> regrasDeTreinos = new ArrayList<>();

    @OneToMany(mappedBy = "exercicioTemplate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExercicioSessao> exerciciosExecucao = new ArrayList<>();

}
