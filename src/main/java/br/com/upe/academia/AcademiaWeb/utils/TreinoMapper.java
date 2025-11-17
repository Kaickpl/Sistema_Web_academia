package br.com.upe.academia.AcademiaWeb.utils;


import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TreinoMapper {

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

}
