package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonalDTOs extends UsuarioDTOs {
    private String cref;

    public PersonalDTOs(Personal personal) {
        super(personal);
        this.cref = personal.getCref();
    }
}
