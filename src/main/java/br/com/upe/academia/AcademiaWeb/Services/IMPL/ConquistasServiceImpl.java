package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
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
    public Conquistas registrarConquista(UUID alunoId, String titulo, String descricao, int moedas) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + alunoId));

        int novoSaldo = aluno.getSaldoMoedas() + moedas;

        aluno.setSaldoMoedas(novoSaldo);

        alunoRepository.save(aluno);

        Conquistas conquistas = new Conquistas(aluno, titulo, descricao, moedas);

        return conquistasRepository.save(conquistas);
    }

    @Override
    public List<Conquistas> mostrarConquistas(UUID alunoId) {
        return conquistasRepository.findByAluno_IdUsuario(alunoId);
    }
}
