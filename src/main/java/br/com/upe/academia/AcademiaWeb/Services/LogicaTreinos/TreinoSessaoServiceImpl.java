package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoSessaoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class TreinoSessaoServiceImpl implements TreinoSessaoService {

    @Autowired
    private TreinoSessaoRepository treinoSessaoRepository;
    @Autowired
    private TreinoSessaoMapper treinoSessaoMapper;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private TreinoService treinoService;


    @Override
    public TreinoSessao buscarSessaoPorId(UUID idTreinoSessao) {
        return treinoSessaoRepository.findById(idTreinoSessao).orElseThrow(() -> new RuntimeException("Sessoa de Treino não encontrada."));
    }

    @Override
    @Transactional
    public TreinoSessao iniciarTreinoSessao(TreinoSessaoDTO treinoSessaoDTO) {

        Aluno aluno = alunoService.buscarAlunoPorId(treinoSessaoDTO.getIdAluno());
        Treino treinoTemplate = treinoService.buscarTreino(treinoSessaoDTO.getIdTreinoTemplate());

        TreinoSessao treinoSessao = treinoSessaoMapper.toEntity(treinoSessaoDTO);

        if(treinoSessao.getDataExecucao()==null){
            treinoSessao.setDataExecucao(Instant.now());
        }

        treinoSessao.setTreinoTemplate(treinoTemplate);
        treinoSessao.setAluno(aluno);

        return treinoSessaoRepository.save(treinoSessao);
    }

    @Override
    public TreinoSessao fecharTreinoSessao(UUID idTreinoSessao) {
        TreinoSessao treinoSessaoAtt = buscarSessaoPorId(idTreinoSessao);
        if(treinoSessaoAtt.isConcluido()){
            throw new IllegalStateException("Esta sessão já foi finalizada");
        }
        treinoSessaoAtt.setConcluido(true);
        treinoSessaoAtt.setTempoFinalizacao(Instant.now());
        return treinoSessaoRepository.save(treinoSessaoAtt);
    }

    @Override
    public void apagarTreinoSessao(UUID idTreinoSessao) {
         this.buscarSessaoPorId(idTreinoSessao);
         treinoSessaoRepository.deleteById(idTreinoSessao);
    }

    @Override
    public TreinoSessao recriarTreinoSessao(TreinoSessao sessaoAntiga){
        sessaoAntiga.setIdTreinoSessao(null);
        sessaoAntiga.setExerciciosRealizados(new ArrayList<>());
        return treinoSessaoRepository.save(sessaoAntiga);
    }

    @Override
    public void adicionarComentario(UUID idTreinoSessao, String comentario) {
        TreinoSessao treino = this.buscarSessaoPorId(idTreinoSessao);
        treino.setComentario(comentario);
    }
}
