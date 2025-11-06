package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;

import java.util.List;
import java.util.UUID;

public interface PersonalService {

    public Personal cadastrarPersonal(PersonalDTOs personalDTOs);


    public Personal alterarPersonal(String cref, PersonalDTOs personalDTOs);

    public void deletarPersonal(String cref);

    public Personal buscarPersonal(String cref);

    public List<Personal> buscarPersonalNome(String nomeUsuario);

    public boolean existeCref(String cref);

    public Boolean validarEmail(String email);

    public Personal TrocaSenha(String email, TrocaSenhaDTOs senhaDTOs);

    public Boolean validarCref(String cref);


}
