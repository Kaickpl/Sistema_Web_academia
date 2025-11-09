package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSerie;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    @JsonIgnore
    private Exercicio exercicio;
}
