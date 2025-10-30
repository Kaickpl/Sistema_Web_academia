package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProgressaoDTOs {
    private UUID idProgressao;
    private UUID alunoId;
    private String nomeExercicio;
    private int peso;
    private LocalDate data;


    public ProgressaoDTOs(Progressao progressao) {
        this.idProgressao = progressao.getIdProgressao();
        this.alunoId = progressao.getAluno().getIdUsuario();
        this.nomeExercicio = progressao.getNomeExercicio();
        this.peso = progressao.getPeso();
    }
}
