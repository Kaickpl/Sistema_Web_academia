package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonalResponseDTOs {
private UUID IdUsuario;
private String nomeUsuario;
private String CREF;
private String email;
private String telefone;
private String grupos;

public PersonalResponseDTOs(Personal personal) {
    this.IdUsuario = personal.getIdUsuario();
    this.nomeUsuario = personal.getNomeUsuario();
    this.CREF = personal.getCref();
    this.email = personal.getEmail();
    this.telefone = personal.getTelefone();
    if (personal.getGrupos() != null) {
        this.grupos = personal.getGrupos().stream().map(grupo -> grupo.getNomeGrupo()) .toList().toString();
    }else {
        this.grupos = "[]";
    }


}

}
