package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AvaliacaoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.AvaliacaoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    MedidasCorporaisRepository medidasCorporaisRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;


    @Override
    public Avaliacao criarAvaliacao(AvaliacaoDTOs avaliacaoDTOs) {
        Aluno aluno = alunoRepository.findById(avaliacaoDTOs.getAlunoId()).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Personal personal = personalRepository.findById(avaliacaoDTOs.getPersonalId()).orElseThrow(() -> new RuntimeException("Personal não encontrado"));
        MedidasCorporais medidasCorporais = medidasCorporaisRepository.findById(avaliacaoDTOs.getMedidasId()).orElseThrow(() -> new RuntimeException("Medidas não encontradas"));

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setAluno(aluno);
        novaAvaliacao.setPersonal(personal);
        novaAvaliacao.setMedidasCorporais(medidasCorporais);
        novaAvaliacao.setObjetivoAvaliacao(avaliacaoDTOs.getObjetivoAvaliacao());
        novaAvaliacao.setDataAvaliacao(avaliacaoDTOs.getDataAvaliacao());
        novaAvaliacao.setDataSolicitacao(avaliacaoDTOs.getDataSolicitacao());
        return avaliacaoRepository.save(novaAvaliacao);
    }

    @Override
    public List<Avaliacao> mostrarAvaliacaoAluno(UUID alunoId) {
        return avaliacaoRepository.findByAluno_IdUsuario(alunoId);
    }

    @Override
    public List<Avaliacao> mostrarAvaliacaoPersonal(String cref) {
        return avaliacaoRepository.findByPersonal_Cref(cref);
    }

    @Override
    public List<Avaliacao> mostrarAvaliacaoPersonalEData(String cref, LocalDate data) {
        return avaliacaoRepository.findByPersonal_CrefAndDataAvaliacao(cref, data);
    }

    @Override
    public boolean removerAvaliacao(UUID idAvaliacao) {
        if(avaliacaoRepository.existsById(idAvaliacao)){
            avaliacaoRepository.deleteById(idAvaliacao);
            return true;
        }
        return false;
    }

    @Override
    public Avaliacao buscarPorId(UUID idAvaliacao) {
        return avaliacaoRepository.findByIdAvaliacao(idAvaliacao);
    }

    @Override
    public Avaliacao alterarDataAvaliacao(UUID idAvaliacao, AvaliacaoDTOs avaliacaoDTOs) {
        Avaliacao avaliacao = avaliacaoRepository.findByIdAvaliacao(idAvaliacao);
        //tirar issoe por no service
        if (avaliacaoDTOs.getDataAvaliacao() != null){
            avaliacao.setDataAvaliacao(avaliacaoDTOs.getDataAvaliacao());
        }
        avaliacao.setDataAvaliacao(avaliacao.getDataAvaliacao());

        return avaliacaoRepository.save(avaliacao);
    }
}
