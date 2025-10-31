package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public boolean validaremail(String email) {
        return alunoRepository.findByEmail(email).isPresent();
    }


    public Aluno cadastrarAluno(AlunoDTOs alunoDTOs) {
        alunoDTOs.setTipo(Tipo.aluno);

        if (alunoRepository.findByEmail(alunoDTOs.getEmail()).isPresent()) {
            return null;
        }

        if (alunoDTOs.getNomeUsuario() == null || alunoDTOs.getNomeUsuario().isEmpty()) {
            return null;
        }
        if (alunoDTOs.getSenha() == null || alunoDTOs.getSenha().isEmpty()) {
            return null;
        }
        if (ValidarGmail(alunoDTOs.getEmail()) == false) {
            return null;

        }

        Aluno aluno =  new Aluno();
        aluno.setEmail(alunoDTOs.getEmail());
        aluno.setSenha(alunoDTOs.getSenha());
        aluno.setDataNascimento(alunoDTOs.getDataNascimento());
        aluno.setTelefone(alunoDTOs.getTelefone());
        aluno.setTipo(alunoDTOs.getTipo());
        aluno.setNomeUsuario(alunoDTOs.getNomeUsuario());
        aluno.setSaldoMoedas(alunoDTOs.getSaldoMoedas());
        return alunoRepository.save(aluno);
    }
    @Override
    public Aluno alterarAluno(UUID id, AlunoDTOs alunoDTOs) {
        Optional<Aluno> idExiste = alunoRepository.findById(id);
        if (idExiste.isEmpty()) {
            return null;
        }
        Aluno alunoEncontrado = idExiste.get();
        if (alunoDTOs.getEmail() != null && !alunoDTOs.getEmail().equals(alunoEncontrado.getEmail())) {
            if (validaremail(alunoDTOs.getEmail())) {
                return null;
            }
            if (ValidarGmail(alunoDTOs.getEmail()) == false) {
                return null;
            }
            alunoEncontrado.setEmail(alunoDTOs.getEmail());
        }
        if (alunoDTOs.getNomeUsuario() != null) {
            alunoEncontrado.setNomeUsuario(alunoDTOs.getNomeUsuario());
        }
        if (alunoDTOs.getTelefone() != null) {
            alunoEncontrado.setTelefone(alunoDTOs.getTelefone());
        }
        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Aluno TrocarSenha(String Email, TrocaSenhaDTOs senhaDTOs){
        Optional<Aluno> emailExiste = alunoRepository.findByEmail(Email);
        if (emailExiste.isEmpty()) {
            return null;
        }
        if(!senhaDTOs.getNovaSenha().equals(senhaDTOs.getConfirmaSenha())){
            return null;
        }
        if (senhaDTOs.getNovaSenha() ==  null || senhaDTOs.getNovaSenha().isEmpty()) {
            return null;
        }
        Aluno alunoEncontrado = emailExiste.get();
        alunoEncontrado.setSenha(senhaDTOs.getConfirmaSenha());
        return alunoRepository.save(alunoEncontrado);
    }

    @Override
    public Boolean ValidarGmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }//replace verificar se so tem gmail
        return email.toLowerCase().contains("@gmail.com") || email.toLowerCase().contains("@upe.br");
    }


    @Override
    public boolean removerAluno(UUID id) {
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Aluno> buscaraluno(String nome) {
        return alunoRepository.findByNomeUsuarioContaining(nome);
    }

    @Override
    public Page<Aluno> ListarAlunos(Pageable page) {
        return alunoRepository.findAll(page);
    }

}

