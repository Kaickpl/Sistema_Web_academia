package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TrocaSenhaDTOs {
    private String NovaSenha;
    private String confirmaSenha;

    public TrocaSenhaDTOs(Aluno aluno) {
        this.NovaSenha = aluno.getSenha();
        this.confirmaSenha = aluno.getSenha();
    }
    public TrocaSenhaDTOs(Personal personal) {
        this.NovaSenha = personal.getSenha();
        this.confirmaSenha = personal.getSenha();
    }
}
