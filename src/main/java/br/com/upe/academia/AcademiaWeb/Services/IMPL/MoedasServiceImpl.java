package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.MoedasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class MoedasServiceImpl implements MoedasService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno adicionarMoedas(UUID alunoId, int quantidade) {
        if (quantidade <= 0) {
            throw new ValorInvalidoException("A quantidade de moedas deve ser maior que zero.");
        }

        Aluno aluno = alunoRepository.findByIdUsuario(alunoId);
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        int novoValor = aluno.getSaldoMoedas() + quantidade;
        aluno.setSaldoMoedas(novoValor);
        return alunoRepository.save(aluno);
    }
}
