package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;
import java.util.UUID;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Aluno cadastrarAluno(Aluno aluno) {
        aluno.setTipo(Tipo.aluno);
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno alterarAluno(Aluno aluno) {
        return null;
    }

    @Override
    public boolean removerAluno(Aluno aluno) {
        return false;
    }

    @Override
    public boolean findById(UUID id) {
        return false;
    }

    @Override
    public Page<Aluno> ListarAlunos(Pageable page) {
        return null;
    }
}
