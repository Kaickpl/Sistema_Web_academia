package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ObjetivosDTO {
    private UUID idObjetivo;
    private UUID alunoId;
    private String tipoMedida;
    private double valorAlvo;
    private String condicao;
    private String status;
    public ObjetivosDTO(Objetivos objetivos){
        this.idObjetivo = objetivos.getIdObjetivo();
        this.alunoId = objetivos.getAluno().getIdUsuario();
        this.tipoMedida = objetivos.getTipoMedida();
        this.valorAlvo = objetivos.getValorAlvo();
        this.condicao = objetivos.getCondicao();
        this.status = objetivos.getStatus();
    }
}
