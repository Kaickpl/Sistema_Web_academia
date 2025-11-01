package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonalResponseGrupoDTOs {
    private UUID idUsuario;
    private String nomeUsuario;
    public PersonalResponseGrupoDTOs(Personal personal) {
        this.idUsuario = personal.getIdUsuario();
        this.nomeUsuario = personal.getNomeUsuario();
    }
}
