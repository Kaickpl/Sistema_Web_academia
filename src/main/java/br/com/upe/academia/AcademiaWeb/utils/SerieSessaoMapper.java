package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import org.springframework.stereotype.Component;

@Component
public class SerieSessaoMapper {
    public SerieSessao toEntity(SerieSessaoDTO serieSessaoDTO) {
        if (serieSessaoDTO == null) return null;

        SerieSessao serieSessao = new SerieSessao();
        serieSessao.setPeso(serieSessaoDTO.getPeso());
        serieSessao.setNumeroDeRepeticoes(serieSessaoDTO.getNumeroDeRepeticoes());

        if(serieSessaoDTO.getIdExercicioSessao() != null){
            ExercicioSessao exercicioSessao = new  ExercicioSessao();
            exercicioSessao.setIdExercicioSessao(serieSessaoDTO.getIdExercicioSessao());
            serieSessao.setExercicioSessao(exercicioSessao);
        }

        return serieSessao;
    }

    public SerieSessaoDTO toDTO(SerieSessao serieSessao) {
        if (serieSessao == null) return null;

        SerieSessaoDTO serieSessaoDTO = new SerieSessaoDTO();
        serieSessaoDTO.setSerieSessaoId(serieSessao.getIdSerieSessao());
        serieSessaoDTO.setPeso(serieSessao.getPeso());
        serieSessaoDTO.setNumeroDeRepeticoes(serieSessao.getNumeroDeRepeticoes());
        serieSessaoDTO.setIdExercicioSessao(serieSessao.getExercicioSessao().getIdExercicioSessao());

        return serieSessaoDTO;
    }
}