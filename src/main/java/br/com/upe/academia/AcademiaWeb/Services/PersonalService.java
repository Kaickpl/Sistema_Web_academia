package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;

import java.util.List;
import java.util.UUID;

public interface PersonalService {

    public Personal cadastrarPersonal(PersonalDTOs personalDTOs);

    public boolean validarEmail(String email);

    public Personal alterarPersonal(String cref, PersonalDTOs personalDTOs);

    public Boolean deletarPersonal(String cref);

    public Personal buscarPersonal(String cref);

    public List<Personal> buscarPersonalNome(String nomeUsuario);

    public boolean validarCref(String cref);

    public Boolean validarGmail(String email);

    public Personal TrocaSenha(UUID id, TrocaSenhaDTOs senhaDTOs);


}
