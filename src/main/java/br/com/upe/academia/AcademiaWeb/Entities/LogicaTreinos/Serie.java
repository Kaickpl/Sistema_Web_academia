package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Column(nullable = false)
    private Integer numeroDeRepeticoes;
    @Column(nullable = false)
    private Float pesoDaSerie;
    private boolean isConcluida = false;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    @JsonIgnore
    private Exercicio exercicio;


    public Float getVolumeSerie() {
        if (numeroDeRepeticoes != null && pesoDaSerie != null) {
            return pesoDaSerie * numeroDeRepeticoes;
        }
        return 0.0f;
    }
}

