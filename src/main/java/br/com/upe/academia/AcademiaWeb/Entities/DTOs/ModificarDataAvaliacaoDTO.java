package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModificarDataAvaliacaoDTO {
    private LocalDate dataAvaliacao;
    public ModificarDataAvaliacaoDTO(Avaliacao avaliacao) {
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
    }
}
