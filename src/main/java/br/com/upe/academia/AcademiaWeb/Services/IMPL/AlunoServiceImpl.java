package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public boolean validaremail(String email) {
        return alunoRepository.findByEmail(email).isPresent();
    }


    public Aluno cadastrarAluno(Aluno aluno) {
        aluno.setTipo(Tipo.aluno);

        if (alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
            return null;
        }

        if (aluno.getNomeUsuario() == null || aluno.getNomeUsuario().isEmpty()) {
            return null;
        }

        return alunoRepository.save(aluno);
    }
    @Override
    public Aluno alterarAluno(Aluno aluno) {

        return null;
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
