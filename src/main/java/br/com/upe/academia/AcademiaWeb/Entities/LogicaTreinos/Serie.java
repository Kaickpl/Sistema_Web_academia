package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/* talvez de pra tirar o mapper, passando o construtor no dto mesmo,
   criar um dto para cada entidade que retorne apenas o necessario, no caso da serie seria o peso, o num de repeticoes e o statuts
*/


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

