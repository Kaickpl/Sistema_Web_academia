package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class PersonalServiceImpl implements PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    @Override
    public Personal cadastrarPersonal(PersonalDTOs personalDTOs) {
        personalDTOs.setTipo(Tipo.personalTrainer);
        if(personalRepository.findByEmail(personalDTOs.getEmail()).isPresent() ){
            return null;
        }
        if(personalRepository.findByCref(personalDTOs.getCref()).isPresent() ){
            return null;
        }
        if (personalDTOs.getSenha() == null || personalDTOs.getSenha().isEmpty()) {
            return null;
        }
        if (personalDTOs.getCref() == null || personalDTOs.getCref().isEmpty()) {
            return null;
        }
        if (validarGmail(personalDTOs.getEmail())== false) {
            return  null;
        }
        Personal personal = new Personal();
        personal.setNomeUsuario(personalDTOs.getNomeUsuario());
        personal.setDataNascimento(personalDTOs.getDataNascimento());
        personal.setTelefone(personalDTOs.getTelefone());
        personal.setTipo(personalDTOs.getTipo());
        personal.setEmail(personalDTOs.getEmail());
        personal.setCref(personalDTOs.getCref());
        personal.setSenha(personalDTOs.getSenha());

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
    public Boolean validarGmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.toLowerCase().contains("@gmail.com")||email.toLowerCase().contains("@upe.br");

    }

    @Override
    public Personal alterarPersonal(String cref, PersonalDTOs personalDTOs) {
        Optional<Personal> crefExiste = personalRepository.findByCref(cref);
       if (crefExiste.isEmpty()) {
           return null;
       }
       Personal personalEncontrado = crefExiste.get();
        if (personalDTOs.getEmail() != null && !personalDTOs.getEmail().equals(personalEncontrado.getEmail())) {
            if (validarEmail(personalDTOs.getEmail())) {
                return null;
            }
            if(validarGmail(personalDTOs.getEmail())==false) {
                return null;
            }
            personalEncontrado.setEmail(personalDTOs.getEmail());
        }
        if (personalDTOs.getCref() != null && !personalDTOs.getCref().equals(personalEncontrado.getCref())) {
            if (!validarCref(personalDTOs.getCref())){
                return null;
            }
            personalEncontrado.setCref(personalDTOs.getCref());
        }
        if (personalDTOs.getNomeUsuario() != null) {
            personalEncontrado.setNomeUsuario(personalDTOs.getNomeUsuario());
        }
        if (personalDTOs.getTelefone() != null) {
            personalEncontrado.setTelefone(personalDTOs.getTelefone());
        }
        Personal personalAtualizado = personalRepository.save(personalEncontrado);
        return personalRepository.save(personalAtualizado);
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

    @Override
    public Personal TrocaSenha(UUID id, TrocaSenhaDTOs senhaDTOs){
        Optional<Personal> personalExiste = personalRepository.findById(id);
        if (personalExiste.isEmpty()) {
            return null;
        }
        if (senhaDTOs.getNovaSenha() == null || senhaDTOs.getNovaSenha().isBlank()
            || senhaDTOs.getConfirmaSenha() == null|| senhaDTOs.getConfirmaSenha().isBlank()) {
            return null;
        }
        if(!senhaDTOs.getNovaSenha().equals(senhaDTOs.getConfirmaSenha())) {
            return null;
        }
        if (senhaDTOs.getConfirmaSenha().equals(personalExiste.get().getSenha())) {
            return null;
        }
        Personal personal = personalExiste.get();
        personal.setSenha(senhaDTOs.getConfirmaSenha());
        return personalRepository.save(personal);
    }

}
