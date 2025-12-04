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
    private String nomeAluno;
    private UUID idAluno;
    private List<TreinoDTO> treinos;

}
