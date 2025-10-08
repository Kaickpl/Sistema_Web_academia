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
    public ProgressaoDTOs() {
    }

    public ProgressaoDTOs(Progressao progressao) {
        this.idProgressao = progressao.getIdProgressao();
        this.alunoId = progressao.getAlunoId();
        this.nomeExercicio = progressao.getNomeExercicio();
        this.peso = progressao.getPeso();
    }

    public UUID getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public UUID getIdProgressao() {
        return idProgressao;
    }

    public void setIdProgressao(UUID idProgressao) {
        this.idProgressao = idProgressao;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
