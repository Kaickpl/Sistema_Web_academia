package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Service

public class PersonalServiceImpl implements PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    @Override
    public Personal cadastrarPersonal(Personal personal) {
        personal.setTipo(Tipo.personalTrainer);
        if(personalRepository.findByEmail(personal.getEmail()).isPresent() ){
            return null;
        }
        if(personalRepository.findByCref(personal.getCref()).isPresent() ){
            return null;
        }

        return personalRepository.save(personal);
    }

    @Override
    public boolean validarEmail(String email) {
        return personalRepository.findByEmail(email).isPresent();
    }
    @Override
    public boolean validarCref(String cref) {
        return personalRepository.findByCref(cref).isEmpty();
    }

    @Override
    public Personal alterarPersonal(UUID id, Personal personal) {
        return null;
    }

    @Override
    @Transactional
    public Boolean deletarPersonal(String cref) {
        if(personalRepository.existsByCref(cref)){
            personalRepository.deleteByCref(cref);
            return true;
        }
        return false;
    }

    @Override
    public Personal buscarPersonal(String cref) {
        return personalRepository.findByCref(cref).orElse(null) ;
    }

    @Override
    public List<Personal> buscarPersonalNome(String nome) {
        return personalRepository.findByNomeUsuarioContaining(nome);

    }
}
