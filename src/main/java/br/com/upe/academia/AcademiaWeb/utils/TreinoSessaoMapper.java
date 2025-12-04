package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TreinoSessaoMapper {

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ExercicioSessaoMapper exercicioSessaoMapper;

    public TreinoSessao toEntity(TreinoSessaoDTO treinoSessaoDTO) {
        if(treinoSessaoDTO == null){
            return null;
        }
        TreinoSessao treinoSessao = new TreinoSessao();
        treinoSessao.setIdTreinoSessao(treinoSessaoDTO.getIdTreinoSessao());

        //Esse mecanismo aqui faz com que o JPA, mesmo que o treinoRef tenha apenas o id,
        //Identifique o treino como o treino que possui o mesmo id no banco de dados , que mecanismo 🧠
        if (treinoSessaoDTO.getIdTreinoTemplate() != null) {
            Treino treinoRef =  new Treino();
            treinoRef.setIdTreino(treinoSessaoDTO.getIdTreinoTemplate());
            treinoSessao.setTreinoTemplate(treinoRef);
        }

        if (treinoSessaoDTO.getIdAluno() != null) {
            Aluno alunoRef = new Aluno();
            alunoRef.setIdUsuario(treinoSessaoDTO.getIdAluno());
            treinoSessao.setAluno(alunoRef);
        }
        return treinoSessao;
    }

    public TreinoSessaoDTO toDto(TreinoSessao treinoSessao) {
        if(treinoSessao == null){
            return null;
        }
        TreinoSessaoDTO treinoSessaoDTO = new TreinoSessaoDTO();
        treinoSessaoDTO.setIdTreinoSessao(treinoSessao.getIdTreinoSessao());

        if(treinoSessao.getTreinoTemplate() != null){
            treinoSessaoDTO.setIdTreinoTemplate(treinoSessao.getTreinoTemplate().getIdTreino());

        }
        if(treinoSessao.getAluno() != null){
            treinoSessaoDTO.setIdAluno((treinoSessao.getAluno().getIdUsuario()));
        }
        return treinoSessaoDTO;
    }

    public TreinoSessaoResponseDTO toResponseDTO(TreinoSessao treinoSessao) {
        if(treinoSessao == null){
            return null;
        }
        TreinoSessaoResponseDTO dto = new TreinoSessaoResponseDTO();
        dto.setNomeAluno(treinoSessao.getAluno().getNomeUsuario());
        dto.setIdTreinoSessao(treinoSessao.getIdTreinoSessao());
        dto.setConfirmarFechamento(treinoSessao.isConcluido());
        dto.setDataFinal(treinoSessao.getTempoFinalizacao());
        dto.setDuration(DurationManager.formatDuration(treinoSessao.getDuracaoTotal()));
        dto.setComentario(treinoSessao.getComentario());
        if (treinoSessao.getExerciciosRealizados() != null) {
            List<ExercicioSessaoResponseDTO> listaExercicios = treinoSessao.getExerciciosRealizados().stream()
                    .map(ex -> exercicioSessaoMapper.toReponseDTO(ex))
                    .collect(Collectors.toList());

            dto.setExercicios(listaExercicios);
        }

        return dto;
    }

    public TreinoSessaoResponseGetDTO toResponseGetDTO(TreinoSessao treinoSessao) {
        if(treinoSessao == null){
            return null;
        }

        TreinoSessaoResponseGetDTO dto = new TreinoSessaoResponseGetDTO();
        dto.setTreinoTemplate(treinoSessao.getTreinoTemplate().getNome());
        dto.setNomeAluno(treinoSessao.getAluno().getNomeUsuario());
        dto.setDuration(DurationManager.formatDuration(treinoSessao.getDuracaoTotal()));
        dto.setDataExecucao(InstantManager.formatInstantToLocalTime(treinoSessao.getDataExecucao()));
        dto.setStatusFechamento(treinoSessao.isConcluido());
        dto.setIdTreinoSessao(treinoSessao.getIdTreinoSessao());
        dto.setComentario(treinoSessao.getComentario());
        return dto;
    }

    public TreinoSessao comentarioToEntity(ComentarioDTO dto) {
        TreinoSessao treinoSessao = new TreinoSessao();
        treinoSessao.setComentario(dto.getComentario());
        return treinoSessao;
    }
}
