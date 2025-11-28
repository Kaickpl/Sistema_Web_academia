package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjetivoExercicioDTO {
    private UUID alunoId;
    private double valorAlvo;
    private double valorAtual;
    private boolean concluido;
    private String tipoObjetivo;
    public ObjetivoExercicioDTO(Objetivos objetivos){
        this.alunoId = objetivos.getAluno().getIdUsuario();
        this.valorAlvo = objetivos.getValorAlvo();
        this.valorAtual = objetivos.getValorAtual();
        this.concluido = objetivos.isConcluido();
    }
}
