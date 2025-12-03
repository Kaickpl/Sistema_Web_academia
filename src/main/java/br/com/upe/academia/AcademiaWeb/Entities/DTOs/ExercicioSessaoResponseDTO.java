package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioSessaoResponseDTO {
    private UUID idExercicioSessao;
    private String nomeExercicio;
    private String comentarioSessao;
    private List<SerieSessaoResponseDTO> seriesExecutadas = new ArrayList<>();

    public double getVolumeTotal() {
        double volumeTotal = 0;
        for(SerieSessaoResponseDTO serieSessao : seriesExecutadas) {
            volumeTotal += serieSessao.getVolumeTotalSerie();
        }
        return volumeTotal;
    }
}
