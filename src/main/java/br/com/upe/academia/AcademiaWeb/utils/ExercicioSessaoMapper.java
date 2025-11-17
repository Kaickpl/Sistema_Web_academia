package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExercicioSessaoMapper {

    @Autowired
    SerieSessaoMapper serieSessaoMapper;

    @Autowired
    ExercicioService exercicioService;

    @Autowired
    TreinoSessaoService treinoSessaoService;

    public ExercicioSessao toEntity(ExercicioSessaoDTO exercicioSessaoDTO) {

        ExercicioSessao entity = new ExercicioSessao();

        entity.setIdExercicioSessao(exercicioSessaoDTO.getIdExercicioSessao());

        if(exercicioSessaoDTO.getIdExercicio() != null) {
            Exercicio exercicioRef = new Exercicio();
            exercicioRef.setIdExercicio(exercicioSessaoDTO.getIdExercicio());
            entity.setExercicioTemplate(exercicioRef);
        }

        if(exercicioSessaoDTO.getIdTreinoSessao() != null) {
            TreinoSessao sessaoPaiRef = new TreinoSessao();
            sessaoPaiRef.setIdTreinoSessao(exercicioSessaoDTO.getIdTreinoSessao());
            entity.setTreinoExecucao(sessaoPaiRef);
        }
        return entity;
    }

    public ExercicioSessaoDTO toDTO(ExercicioSessao entity) {
        if (entity == null) return null;
        ExercicioSessaoDTO dto = new ExercicioSessaoDTO();
        dto.setIdExercicioSessao(entity.getIdExercicioSessao());

        if(entity.getTreinoExecucao() != null) {
            dto.setIdTreinoSessao(entity.getTreinoExecucao().getIdTreinoSessao());
        }

        if(entity.getExercicioTemplate() != null) {
            dto.setIdExercicio(entity.getExercicioTemplate().getIdExercicio());
        }
        return dto;
    }

    public ExercicioSessaoResponseDTO toReponseDTO(ExercicioSessao entity) {
        ExercicioSessaoResponseDTO dto = new ExercicioSessaoResponseDTO();
        dto.setNomeExercicio(entity.getExercicioTemplate().getNomeExercicio());
        dto.setComentarioSessao(entity.getComentario());
        if(entity.getTreinoExecucao() != null) {
            List<SerieSessaoResponseDTO> seriesDTOs = entity.getSeriesRealizadas().stream().map(serie -> serieSessaoMapper.toRespondeDTO(serie)).collect(Collectors.toList());
            dto.setSeriesExecutadas(seriesDTOs);
        }
        return dto;
    }

    public ExercicioSessao comentarioToEntity(ComentarioDTO dto) {
        ExercicioSessao entity = new ExercicioSessao();
        entity.setComentario(dto.getComentario());
        return entity;
    }
}
