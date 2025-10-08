package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConquistasDTOs {

    private UUID idConquistas;
    public ConquistasDTOs(Conquistas conquistas) {
        this.idConquistas = conquistas.getIdConquistas();
    }
}
