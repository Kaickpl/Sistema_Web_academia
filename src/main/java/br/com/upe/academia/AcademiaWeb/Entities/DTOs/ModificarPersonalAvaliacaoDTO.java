package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
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
public class ModificarPersonalAvaliacaoDTO {
    private String email;

    public ModificarPersonalAvaliacaoDTO(Avaliacao avaliacao) {
        this.email = avaliacao.getPersonal().getEmail();
    }
}
