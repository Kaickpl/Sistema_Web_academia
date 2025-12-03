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
public class ObjetivoRegistroDTO {
    private UUID alunoId;
    private String tipoMedida;
    private double valorAlvo;
    private double valorAtual;
    private boolean concluido;
    public ObjetivoRegistroDTO(Objetivos objetivos){
        this.alunoId = objetivos.getAluno().getIdUsuario();
        this.tipoMedida = objetivos.getTipoMedida();
        this.valorAlvo = objetivos.getValorAlvo();
        this.valorAtual = objetivos.getValorAtual();
        this.concluido = objetivos.isConcluido();
    }
}
