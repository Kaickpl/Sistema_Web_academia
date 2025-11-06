package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoTreinosResponseDTO {
    private UUID idAluno;
    private String nomeAluno;
    private List<TreinoDTO> treinos;

    public AlunoTreinosResponseDTO(Aluno aluno) {
        this.idAluno = aluno.getIdUsuario();
        this.nomeAluno = aluno.getNomeUsuario();
        this.treinos = aluno.getTreinosAtribuidos().stream().map(TreinoDTO :: new).collect(Collectors.toList());
    }
}
