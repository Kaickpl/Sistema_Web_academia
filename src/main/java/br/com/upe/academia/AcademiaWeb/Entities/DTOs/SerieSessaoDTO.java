package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.*;
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
}
