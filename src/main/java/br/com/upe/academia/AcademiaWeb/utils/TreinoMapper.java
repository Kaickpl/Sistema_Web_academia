package br.com.upe.academia.AcademiaWeb.utils;


import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoCompletoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TreinoMapper {

    private final TreinoExercicioMapper treinoExercicioMapper;

    public TreinoMapper(TreinoExercicioMapper treinoExercicioMapper) {
        this.treinoExercicioMapper = treinoExercicioMapper;
    }

    public Treino toEntity(TreinoDTO treinoDTO) {
        if (treinoDTO == null)
            return null;
        Treino treino = new Treino();

        treino.setNome(treinoDTO.getNome());
        return treino;
    }

    public TreinoDTO toDTO(Treino treino) {
        if (treino == null)
            return null;
        TreinoDTO treinoDTO = new TreinoDTO();

        treinoDTO.setIdTreino(treino.getIdTreino());
        treinoDTO.setNome(treino.getNome());
        return treinoDTO;
    }

    public TreinoCompletoResponseDTO toTreinoCompletoResponseDTO(Treino treino) {
        if (treino == null)
            return null;
        TreinoCompletoResponseDTO dto = new TreinoCompletoResponseDTO();
        dto.setNomeTreino(treino.getNome());
        dto.setIdTreino(treino.getIdTreino());

        if(treino.getRegrasDeExercicios() != null){
            List<TreinoExercicioResponseDTO> regrasDTOs = treino.getRegrasDeExercicios().stream()
                    .map(regra -> treinoExercicioMapper.toResponseDTO(regra))
                    .collect(Collectors.toList());

            dto.setTreinoExercicioInfos(regrasDTOs);
        }

        return dto;
    }

}
