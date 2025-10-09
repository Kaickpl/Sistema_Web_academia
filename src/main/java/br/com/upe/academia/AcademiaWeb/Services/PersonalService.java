package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;

import java.util.List;
import java.util.UUID;

public interface PersonalService {

    public Personal cadastrarPersonal(Personal personal);

    public boolean validarEmail(String email);

    public Personal  alterarPersonal(UUID id, Personal personal);

    public Boolean deletarPersonal(String cref);

    public Personal buscarPersonal(String cref);

    public List<Personal> buscarPersonalNome(String nomeUsuario);

    public boolean validarCref(String cref);


}
