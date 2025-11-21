package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.MusculoTrabalhado;
import br.com.upe.academia.AcademiaWeb.utils.DurationCustomConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MusculoTrabalhado musculoPrincipal;

    @OneToMany(mappedBy ="exercicioTemplate", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TreinoExercicio> regrasDeTreinos = new ArrayList<>();

    @OneToMany(mappedBy = "exercicioTemplate", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExercicioSessao> exerciciosExecucao = new ArrayList<>();

}
