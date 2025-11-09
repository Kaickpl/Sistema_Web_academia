package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class SerieSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSerieSessao;
    private double peso;
    private Integer numeroDeRepeticoes;

    public double getPesoTotal(){
        double pesoTotal = (double) peso*numeroDeRepeticoes;
        return pesoTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_sessao_id")
    private ExercicioSessao exercicioSessao;
}
