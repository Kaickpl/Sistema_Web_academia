package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProgressaoDTOs {
    private UUID idProgressao;
    public ProgressaoDTOs(Progressao progressao) {
        this.idProgressao = progressao.getIdProgressao();
    }
}
