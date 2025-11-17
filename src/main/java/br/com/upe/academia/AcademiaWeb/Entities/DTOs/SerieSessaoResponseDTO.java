package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SerieSessaoResponseDTO {
    private double peso;
    private Integer numeroDeRepeticoes;

    public double getVolumeTotalSerie(){
        return peso*numeroDeRepeticoes;
    }

}
