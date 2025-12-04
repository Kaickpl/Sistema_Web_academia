package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonalService {

    public Personal cadastrarPersonal(PersonalDTOs personalDTOs);


    public Personal alterarPersonal(UUID id, PersonalDTOs personalDTOs);

    public void deletarPersonal(UUID id);

    public Personal buscarPersonal(String cref);

    public List<Personal> buscarPersonalNome(String nomeUsuario);

    public boolean existeCref(String cref);

    public Boolean validarEmail(String email);

    public Personal TrocaSenha(TrocaSenhaDTOs senhaDTOs);

    public Boolean validarCref(String cref);

    public List<Grupo> ListaGruposPersonal(UUID idPersonal);

    public Personal buscarPersonalPorEmail(String email);

    public Optional<Personal> buscarPersonalEmail(String email);

    public PersonalResponseDTOs VerPerfil(UUID idPersonal);
}
