package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlunoResponseDTOs  {
    private UUID IdUsuario;
    private String nomeUsuario;
    private String email;
    private String telefone;
    private int saldoMoedas;

    public AlunoResponseDTOs(Aluno aluno) {
        this.IdUsuario = aluno.getIdUsuario();
        this.nomeUsuario = aluno.getNomeUsuario();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.saldoMoedas = aluno.getSaldoMoedas();


    }
}
