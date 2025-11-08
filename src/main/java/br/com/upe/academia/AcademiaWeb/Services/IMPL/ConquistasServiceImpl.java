package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistasDTOs;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioExistenteException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ConquistasRepository;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConquistasServiceImpl implements ConquistasService {
    @Autowired
    ConquistasRepository conquistasRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Conquistas registrarConquista(ConquistasDTOs conquistasDTOs) {

        Aluno aluno = alunoRepository.findByIdUsuario(conquistasDTOs.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        if (conquistasDTOs.getMoedas() <= 0){
            throw new ValorInvalidoException("O valor de moedas deve ser maior que zero");
        }

        int novoSaldo = aluno.getSaldoMoedas() + conquistasDTOs.getMoedas();

        aluno.setSaldoMoedas(novoSaldo);

        alunoRepository.save(aluno);

        Conquistas conquistas = new Conquistas(aluno, conquistasDTOs.getNomeConquista(), conquistasDTOs.getDescricaoConquista(), conquistasDTOs.getMoedas());

        return conquistasRepository.save(conquistas);
    }

    @Override
    public List<Conquistas> mostrarConquistas(UUID alunoId) {
        Aluno aluno = alunoRepository.findByIdUsuario(alunoId);
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        return conquistasRepository.findByAluno_IdUsuario(alunoId);
    }
}
