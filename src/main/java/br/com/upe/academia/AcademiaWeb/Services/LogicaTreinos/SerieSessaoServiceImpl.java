package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.*;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.SerieSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SerieSessaoServiceImpl implements SerieSessaoService {
    @Autowired
    private SerieSessaoRepository serieSessaoRepository;

    @Autowired
    private SerieSessaoMapper serieSessaoMapper;

    @Autowired
    private GerenciaConquistas gerenciaConquistas;

    @Autowired
    private ExercicioSessaoRepository exercicioSessaoRepository;

    @Autowired
    private MedidasCorporaisService medidasCorporaisService;

    @Override
    public SerieSessao buscarSerieSessao(UUID idSerieSessao) {
        return serieSessaoRepository.findById(idSerieSessao).orElseThrow(() -> new RuntimeException("Série de Sessão não encontrada."));
    }



    @Override
    @Transactional
    public SerieSessao salvarSerieSessao(SerieSessaoDTO serieSessaoDTO) {
        ExercicioSessao exercicioSessaoPai = exercicioSessaoRepository.findById(serieSessaoDTO.getIdExercicioSessao())
                .orElseThrow(() -> new RuntimeException("ExercicioSessao pai não encontrado"));
        SerieSessao serieParaSalvar = serieSessaoMapper.toEntity(serieSessaoDTO);
        serieParaSalvar.setExercicioSessao(exercicioSessaoPai);
        SerieSessao novaSerieSessao = serieSessaoRepository.save(serieParaSalvar);
        UUID alunoId = exercicioSessaoPai.getTreinoExecucao().getAluno().getIdUsuario();
        String nomeExercicio = exercicioSessaoPai.getExercicioTemplate().getNomeExercicio();

        SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO = new SessaoProgressaoResponseDTO();
        sessaoProgressaoResponseDTO.setPeso(serieSessaoDTO.getPeso());
        sessaoProgressaoResponseDTO.setAlunoId(alunoId);
        sessaoProgressaoResponseDTO.setNumeroDeRepeticoes(serieSessaoDTO.getNumeroDeRepeticoes());
        sessaoProgressaoResponseDTO.setNomeExercicio(nomeExercicio);

        double pesoAluno = medidasCorporaisService.mostrarMedidasAtuais(alunoId).getPeso();

        gerenciaConquistas.decisaoConquista(sessaoProgressaoResponseDTO,pesoAluno);

        return novaSerieSessao;
    }

    @Override
    public void removerSerieSessao(UUID idSerieSessao) {
        serieSessaoRepository.deleteById(idSerieSessao);
    }

    @Override
    public double calcularVolumeTotal(UUID idSerieSessao) {
        return this.buscarSerieSessao(idSerieSessao).getPesoTotal();
    }

    @Override
    public SerieSessao editarSerieSessao(SerieSessaoDTO serieSessaoDTO) {
        SerieSessao serieAtt = this.buscarSerieSessao(serieSessaoDTO.getIdSerieSessao());
        serieAtt.setPeso(serieSessaoDTO.getPeso());
        serieAtt.setNumeroDeRepeticoes(serieSessaoDTO.getNumeroDeRepeticoes());
        return serieSessaoRepository.save(serieAtt);
    }

    @Override
    public SerieSessao salvarEntidade(SerieSessao serieSessao) {
        return serieSessaoRepository.save(serieSessao);
    }

    @Override
    public SerieSessaoResponseDTO buscarRecordPorExercicio(UUID idExercicioTemplate, UUID idAluno){
        Pageable limitOne = PageRequest.of(0, 1);
        List<SerieSessao> resultados = serieSessaoRepository.findRecordeDeCarga(idExercicioTemplate, idAluno, limitOne);

        if (resultados.isEmpty()) {
            return null;
        }

        SerieSessao recorde = resultados.get(0);
        return serieSessaoMapper.toRespondeDTO(recorde);
    }

}
