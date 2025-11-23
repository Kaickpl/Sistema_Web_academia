package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreinoExercicioMapper {

    public TempoDeDescansoResponseDTO toTempoDescansoDTO(TreinoExercicio treinoExercicio) {
        if (treinoExercicio == null) return null;
        TempoDeDescansoResponseDTO dto = new TempoDeDescansoResponseDTO();
        dto.setTempoDeDescanso(DurationManager.formatDuration(treinoExercicio.getTempoDeDescanso()));
        return dto;
    }

    public TreinoExercicio toEntity(TreinoExercicioDTO dto) {
        if(dto == null) return null;
        TreinoExercicio treinoExercicio = new TreinoExercicio();
        treinoExercicio.setIdTreinoExercicio(dto.getIdTreinoExercicio());
        treinoExercicio.setTempoDeDescanso(DurationManager.parseDuration(dto.getTempoDeDescanso()));

        if(dto.getIdExercicio() != null){
            Exercicio exercicioReff = new Exercicio();
            exercicioReff.setIdExercicio(dto.getIdExercicio());
            treinoExercicio.setExercicioTemplate(exercicioReff);
        }

        if(dto.getIdTreino() != null){
            Treino treinoReff = new Treino();
            treinoReff.setIdTreino(dto.getIdTreino());
            treinoExercicio.setTreino(treinoReff);
        }

        return  treinoExercicio;
    }

    public TreinoExercicioDTO toDto(TreinoExercicio treinoExercicio) {
        if(treinoExercicio == null) return null;
        TreinoExercicioDTO dto = new TreinoExercicioDTO();
        dto.setIdTreinoExercicio(treinoExercicio.getIdTreinoExercicio());
        dto.setTempoDeDescanso(DurationManager.formatDuration(treinoExercicio.getTempoDeDescanso()));

        if(treinoExercicio.getExercicioTemplate() != null){
            dto.setIdExercicio(treinoExercicio.getExercicioTemplate().getIdExercicio());
        }

        if(treinoExercicio.getTreino() != null){
            dto.setIdTreino(treinoExercicio.getTreino().getIdTreino());
        }

        return dto;
    }

    public TreinoExercicioResponseDTO toResponseDTO(TreinoExercicio treinoExercicio) {
        if(treinoExercicio == null) return null;

        TreinoExercicioResponseDTO dto = new TreinoExercicioResponseDTO();
        dto.setTempoDeDescanso(DurationManager.formatDuration(treinoExercicio.getTempoDeDescanso()));

        if(treinoExercicio.getExercicioTemplate() != null){
            dto.setNomeDoExercicio(treinoExercicio.getExercicioTemplate().getNomeExercicio());
            dto.setDescricaoExercicio(treinoExercicio.getExercicioTemplate().getDescricaoExercicio());
        }

        if(treinoExercicio.getSeriesTemplate() != null){
            int numeroDeSeries = treinoExercicio.getSeriesTemplate().size();
            dto.setQuantidadeSeries(numeroDeSeries);
        }
        return dto;
    }
}
