package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Personal;

public class PersonalDTOs extends UsuarioDTOs {
    private String cref;
    public PersonalDTOs(Personal personal) {
        super(personal);
        this.cref = personal.getCref();
    }
}
