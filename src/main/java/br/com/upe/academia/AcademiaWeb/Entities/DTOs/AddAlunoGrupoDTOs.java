package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;

import java.util.UUID;

public class AddAlunoGrupoDTOs {
    private UUID idUsuario;

    public AddAlunoGrupoDTOs(Aluno aluno) {
        this.idUsuario = aluno.getIdUsuario();
    }
}
