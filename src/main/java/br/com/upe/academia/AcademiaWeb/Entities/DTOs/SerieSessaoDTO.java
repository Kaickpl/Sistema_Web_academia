package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SerieSessaoDTO {
    private UUID serieSessaoId;
    private double peso;
    private Integer numeroDeRepeticoes;
    private UUID idExercicioSessao;

    public double getPesoTotal(){
        return (double) peso*numeroDeRepeticoes;
    }

    public void setPesoTotal(double peso){
        this.peso = peso;
    }
}
