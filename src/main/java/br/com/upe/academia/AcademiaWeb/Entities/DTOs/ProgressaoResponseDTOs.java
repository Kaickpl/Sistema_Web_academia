package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
public class ProgressaoResponseDTOs {
    private UUID idProgressao;
    private String nomeExercicio;
    private int peso;
    private int repeticoes;
    private LocalDate data;

    public ProgressaoResponseDTOs(Progressao progressao){
        this.idProgressao = progressao.getIdProgressao();
        this.nomeExercicio = progressao.getNomeExercicio();
        this.peso = progressao.getPeso();
        this.repeticoes = progressao.getRepeticoes();
        this.data = progressao.getData();
    }
}
