package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Exceptions.CampoObrigatorioException;
import br.com.upe.academia.AcademiaWeb.Exceptions.CrefInvalidoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.EmailInvalidoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioExistenteException;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class PersonalServiceImpl implements PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    @Override
    public Personal cadastrarPersonal(PersonalDTOs personalDTOs) {
        personalDTOs.setTipo(Tipo.personalTrainer);
        if(personalRepository.findByEmail(personalDTOs.getEmail()).isPresent() ){
            throw new UsuarioExistenteException("Usuario já cadastrado com  este email: "+ personalDTOs.getEmail());
        }
        if(personalDTOs.getEmail() == null || personalDTOs.getEmail().isBlank()) {
            throw new CampoObrigatorioException("O campo de e-mail é obrigatório.");
        }
        if(personalRepository.findByCref(personalDTOs.getCref()).isPresent() ){
            throw new UsuarioExistenteException("Usuário já cadastrado com este CREF: "+ personalDTOs.getCref());
        }
        if (personalDTOs.getCref() == null || personalDTOs.getCref().isEmpty()) {
            throw new CampoObrigatorioException("O campo de CREF é obrigatório.");
        }
        if (!validarCref(personalDTOs.getCref())) {
            throw new CrefInvalidoException("O CREF informado é inválido. Use o formato correto, por exemplo: 12345-G/PE.");
        }
        if (personalDTOs.getSenha() == null || personalDTOs.getSenha().isBlank()) {
            throw new CampoObrigatorioException("O campo de senha é obrigatório.");
        }
        if (personalDTOs.getNomeUsuario() == null || personalDTOs.getNomeUsuario().isBlank()) {
            throw new CampoObrigatorioException("O campo de nome de usuário é obrigatório.");
        }
        if (!validarEmail(personalDTOs.getEmail())) {
            throw new EmailInvalidoException("Formato de e-mail inválido. Informe um e-mail no formato nome@dominio.com.");
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
    public boolean existeEmail(String email) {
        return personalRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existeCref(String cref) {
        return personalRepository.findByCref(cref).isEmpty();
    }

    @Override
    public Boolean validarCref(String cref) {
        Pattern p = Pattern.compile("^(CREF\\s*)?\\d{4,6}-[A-Z]/[A-Z]{2}$");
        Matcher m = p.matcher(cref);
        return m.matches();
    }

    @Override
    public Boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public Personal alterarPersonal(String cref, PersonalDTOs personalDTOs) {
        Optional<Personal> crefExiste = personalRepository.findByCref(cref);
       if (crefExiste.isEmpty()) {
           return null;
       }
       Personal personalEncontrado = crefExiste.get();
        if (personalDTOs.getEmail() != null && !personalDTOs.getEmail().equals(personalEncontrado.getEmail())) {
            if (existeEmail(personalDTOs.getEmail())) {
                return null;
            }
            if(validarEmail(personalDTOs.getEmail())==false) {
                return null;
            }
            personalEncontrado.setEmail(personalDTOs.getEmail());
        }
        if (personalDTOs.getCref() != null && !personalDTOs.getCref().equals(personalEncontrado.getCref())) {
            if (!existeCref(personalDTOs.getCref())){
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
