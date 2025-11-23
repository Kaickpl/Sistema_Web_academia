package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TreinoExercicioResponseDTO {
    private String tempoDeDescanso;
    private String nomeDoExercicio;
    private String descricaoExercicio;
    private int quantidadeSeries;
}
