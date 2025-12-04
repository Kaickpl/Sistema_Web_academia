package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivosResponseDTO {
    private String tipoMedida;
    private double valorAlvo;
    private double valorAtual;
    private boolean concluido;
    public ObjetivosResponseDTO(Objetivos objetivos){
        this.tipoMedida = objetivos.getTipoMedida();
        this.valorAlvo = objetivos.getValorAlvo();
        this.valorAtual = objetivos.getValorAtual();
        this.concluido = objetivos.isConcluido();
    }
}
