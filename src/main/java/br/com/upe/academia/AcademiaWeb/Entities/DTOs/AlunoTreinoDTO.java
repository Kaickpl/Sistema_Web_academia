package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class AlunoTreinoDTO {
    UUID idAluno;
    String nomeAluno;
    List<TreinoDTO> treinos;

    public AlunoTreinoDTO(Aluno aluno){
        idAluno = aluno.getIdUsuario();
        nomeAluno = aluno.getNomeUsuario();
        treinos = new ArrayList<>();
    }

}
