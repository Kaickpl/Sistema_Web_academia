package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Exceptions.CampoObrigatorioException;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.OperacaoNaoPermitidaException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoRepository;
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
import java.util.List;
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

    @Autowired
    private ExercicioSessaoRepository exercicioSessaoRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @Override
    public TreinoSessao buscarSessaoPorId(UUID idAluno, UUID idTreinoSessao) {
        TreinoSessao treino = treinoSessaoRepository.findById(idTreinoSessao).orElseThrow(() -> new InformacaoNaoEncontradoException(
                "Sessão de treino não encontrada com ID " +  idTreinoSessao
        ));

        if(!treino.getAluno().getIdUsuario().equals(idAluno)){
            throw new ValorInvalidoException("A sessão de treino a ser encontrada não pertence ao aluno informado na URL.");
        }

        return treino;
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
    public TreinoSessao fecharTreinoSessao(UUID idAluno,UUID idTreinoSessao) {
        TreinoSessao treinoSessaoAtt = buscarSessaoPorId(idAluno, idTreinoSessao);
        if(treinoSessaoAtt.isConcluido()){
            throw new OperacaoNaoPermitidaException("Esta Sessão já foi finalizada e não pode ser alterada.");
        }

        Aluno aluno = alunoService.buscarAlunoPorId(idAluno);

        if (aluno.getIdUsuario() != idAluno){
            throw new ValorInvalidoException("A sessão de treino a ser fechada não pertence ao aluno informado na URL.");
        }

        treinoSessaoAtt.setConcluido(true);
        treinoSessaoAtt.setTempoFinalizacao(Instant.now());
        return treinoSessaoRepository.save(treinoSessaoAtt);
    }

    @Override
    public void apagarTreinoSessao(UUID idTreinoSessao) {
        TreinoSessao treinoSessao = treinoSessaoRepository.findById(idTreinoSessao).orElseThrow(() -> new InformacaoNaoEncontradoException("Sessão de treino não encontrada com ID " +  idTreinoSessao));
         treinoSessaoRepository.deleteById(idTreinoSessao);
    }

    @Override
    public TreinoSessao recriarTreinoSessao(TreinoSessao sessaoAntiga){
        sessaoAntiga.setIdTreinoSessao(null);
        sessaoAntiga.setExerciciosRealizados(new ArrayList<>());
        return treinoSessaoRepository.save(sessaoAntiga);
    }

    @Override
    @Transactional
    public TreinoSessao adicionarComentario(UUID idTreinoSessao, UUID idAluno ,String comentario) {
        TreinoSessao treino = this.buscarSessaoPorId(idAluno, idTreinoSessao);
        treino.setComentario(comentario);

        if (!treino.getAluno().getIdUsuario().equals(idAluno)) {
            throw new ValorInvalidoException("A sessão de treino informada não pertence ao aluno informado na URL.");
        }

        if(comentario==null){
            throw new CampoObrigatorioException("Adicione algum comentário para ser postado!");
        }
        return treinoSessaoRepository.save(treino);
    }

    @Override
    public TreinoSessao salvarEntidade(TreinoSessao treinoSessao) {
        return treinoSessaoRepository.save(treinoSessao);
    }

    @Override
    public void restaurarExecucaoCompleta(TreinoSessao novaSessao, List<ExercicioSessao> exerciciosSalvos) {
        for (ExercicioSessao exercicio : exerciciosSalvos) {

            exercicio.setIdExercicioSessao(null);

            exercicio.setTreinoExecucao(novaSessao);

            if (exercicio.getSeriesRealizadas() != null) {
                for (SerieSessao serie : exercicio.getSeriesRealizadas()) {
                    serie.setIdSerieSessao(null);
                    serie.setExercicioSessao(exercicio);
                }
            }

            exercicioSessaoRepository.save(exercicio);
        }
    }

    @Override
    public TreinoSessao buscarSessaoPorIdUnico(UUID idTreinoSessao) {
        return treinoSessaoRepository.findById(idTreinoSessao).orElseThrow(() -> new InformacaoNaoEncontradoException("Sessão de treino não encontrada com ID " +  idTreinoSessao));
    }
}
