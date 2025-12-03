package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Exceptions.*;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String password = passwordEncoder.encode(personalDTOs.getSenha());
        personal.setSenha(password);

        return personalRepository.save(personal);
    }

    @Override
    public Personal alterarPersonal(String cref, PersonalDTOs personalDTOs) {
        Optional<Personal> crefExiste = personalRepository.findByCref(cref);
       if (crefExiste.isEmpty()) {
           throw new UsuarioExistenteException("Nenhum usuário encontrado com o CREF: " + cref);
       }
       Personal personalEncontrado = crefExiste.get();
        if (personalDTOs.getEmail() != null && !personalDTOs.getEmail().equals(personalEncontrado.getEmail())) {
            if (personalRepository.findByEmail(personalDTOs.getEmail()).isPresent()) {
               throw new UsuarioExistenteException("Usuário já cadastrado com esse email: "+ personalDTOs.getEmail());
            }
            if(!validarEmail(personalDTOs.getEmail())) {
                throw new EmailInvalidoException("Formato de e-mail inválido. Informe um e-mail no formato nome@dominio.com.");
            }
            personalEncontrado.setEmail(personalDTOs.getEmail());
        }
        if (personalDTOs.getCref() != null) {
            if (personalDTOs.getCref().equals(personalEncontrado.getCref())) {
                throw new OperacaoNaoPermitidaException("O CREF é igual ao atual. Nenhuma alteração foi realizada.");
            }
            if (existeCref(personalDTOs.getCref())){
                throw new UsuarioExistenteException("Já existe um personal cadastrado com este CREF: " + personalDTOs.getCref());
            }
            if (!validarCref(personalDTOs.getCref())) {
                throw new CrefInvalidoException("Formato de CREF inválido. Informe um CREF no formato 12345-G/PE.");
            }
            personalEncontrado.setCref(personalDTOs.getCref());
        }
        if (personalDTOs.getNomeUsuario() != null) {
            if (personalDTOs.getNomeUsuario().isBlank()){
                throw new CampoObrigatorioException("O campo 'nome de usuário' não pode ser vazio ou nulo.");
            }
            if (personalDTOs.getNomeUsuario().equals(personalEncontrado.getNomeUsuario())) {
                throw new OperacaoNaoPermitidaException("O nome do usuário é igual ao atual. Nenhuma alteração foi realizada.");
            }
            personalEncontrado.setNomeUsuario(personalDTOs.getNomeUsuario());
        }
        if (personalDTOs.getTelefone() != null) {
            if (personalDTOs.getTelefone().equals(personalEncontrado.getTelefone())){
                throw new OperacaoNaoPermitidaException("O número de telefone informado é igual ao atual. Nenhuma alteração foi realizada.");
            }
            personalEncontrado.setTelefone(personalDTOs.getTelefone());
        }
        if (personalDTOs.getDataNascimento() != null) {
            if (personalEncontrado.getDataNascimento()!=null){
                throw new OperacaoNaoPermitidaException("A data de nascimento já foi definida e não pode ser modificada.");
            }
        }
        return personalRepository.save(personalEncontrado);
    }

    @Override
    @Transactional
    public void deletarPersonal(String cref) {
        if(!personalRepository.existsByCref(cref)){
           throw new InformacaoNaoEncontradoException("Nenhum aluno cadastrado com esse ID: " + cref);

        }
        personalRepository.deleteByCref(cref);
    }

    @Override
    public List<Personal> buscarPersonalNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new CampoObrigatorioException("O campo de nome para busca é obrigatório.");
        }
        List<Personal> personais = personalRepository.findByNomeUsuarioContaining(nome);
        if (personais.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum personal com esse nome: " + nome);
        }
        return personais;
    }

    @Override
    public Personal TrocaSenha( TrocaSenhaDTOs senhaDTOs){
        Optional<Personal> personalExiste = personalRepository.findByEmail(senhaDTOs.getEmail());
        if (personalExiste.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum personal com esse email: " + senhaDTOs.getEmail());

        }
        if (senhaDTOs.getNovaSenha() == null || senhaDTOs.getNovaSenha().isBlank()
            || senhaDTOs.getConfirmaSenha() == null|| senhaDTOs.getConfirmaSenha().isBlank()) {
            throw new CampoObrigatorioException("Os campos de senha e confirmação de senha são obrigatórios.");

        }
        if(!senhaDTOs.getNovaSenha().equals(senhaDTOs.getConfirmaSenha())) {
            throw new OperacaoNaoPermitidaException("As senhas informadas não conferem.");
        }
        if (senhaDTOs.getConfirmaSenha().equals(personalExiste.get().getSenha())) {
            throw new OperacaoNaoPermitidaException("A nova senha não pode ser igual a atual.");

        }
        Personal personal = personalExiste.get();
        personal.setSenha(passwordEncoder.encode(senhaDTOs.getNovaSenha()));
        return personalRepository.save(personal);
    }

    @Override
    public boolean existeCref(String cref) {
        return personalRepository.findByCref(cref).isPresent();
    }
    @Override
    public Boolean validarCref(String cref) {
        Pattern p = Pattern.compile("^(CREF\\s*)?\\d{4,6}-[A-Z]/[A-Z]{2}$");
        Matcher m = p.matcher(cref);
        return m.matches();
    }

    @Override
    public List<Grupo> ListaGruposPersonal(UUID idPersonal) {
        Optional<Personal> personal = personalRepository.findById(idPersonal);
        if (personal.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Nenhum personal com esse ID: " + idPersonal);
        }
        List<Grupo> grupos = personal.get().getGrupos();
        if (grupos.isEmpty()) {
            throw new OperacaoNaoPermitidaException("Personal não possui grupos.");
        }
        return grupos;
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
    public Personal buscarPersonal(String cref) {
        return personalRepository.findByCref(cref).orElse(null) ;
    }

    @Override
    public Personal buscarPersonalPorEmail(String email){
        return personalRepository.findByEmail(email).orElse(null) ;
    }

}
