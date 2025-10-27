package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponseGrupoDTOs {
    private UUID idUsuario;
    private String nomeUsuario;
    public AlunoResponseGrupoDTOs(Aluno aluno) {
        this.idUsuario = aluno.getIdUsuario();
        this.nomeUsuario = aluno.getNomeUsuario();
    }
}

