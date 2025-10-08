package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;


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
    private Integer numeroDeRepeticoes;
    private Float pesoDaSerie;
    private boolean isConcluida = false;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;


    public Float getVolumeSerie() {
        if (numeroDeRepeticoes != null && pesoDaSerie != null) {
            return pesoDaSerie * numeroDeRepeticoes;
        }
        return 0.0f;
    }

    public boolean isConcluida() {
        return isConcluida;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public UUID getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(UUID idSerie) {
        this.idSerie = idSerie;
    }

    public void setConcluida(boolean concluida) {
        isConcluida = concluida;
    }

    public Integer getNumeroDeRepeticoes() {
        return numeroDeRepeticoes;
    }

    public void setNumeroDeRepeticoes(Integer numeroDeRepeticoes) {
        this.numeroDeRepeticoes = numeroDeRepeticoes;
    }

    public Float getPesoDaSerie() {
        return pesoDaSerie;
    }

    public void setPesoDaSerie(Float pesoDaSerie) {
        this.pesoDaSerie = pesoDaSerie;
    }
}
