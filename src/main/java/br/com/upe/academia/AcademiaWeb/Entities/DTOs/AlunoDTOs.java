package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlunoDTOs extends UsuarioDTOs{

    private int saldoMoedas;

    public AlunoDTOs(Aluno aluno) {
        super(aluno);
        this.saldoMoedas = aluno.getSaldoMoedas();
    }
}
