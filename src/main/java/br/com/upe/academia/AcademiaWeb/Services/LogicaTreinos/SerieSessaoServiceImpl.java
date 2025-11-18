package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.SerieSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SerieSessaoServiceImpl implements SerieSessaoService {
    @Autowired
    private SerieSessaoRepository serieSessaoRepository;

    @Autowired
    private SerieSessaoMapper serieSessaoMapper;

    @Autowired
    private MedidasCorporaisRepository medidasCorporaisRepository;
    @Autowired
    private GerenciaConquistas gerenciaConquistas;

    @Override
    public SerieSessao buscarSerieSessao(UUID idSerieSessao) {
        return serieSessaoRepository.findById(idSerieSessao).orElseThrow(() -> new RuntimeException("Série de Sessão não encontrada."));
    }

    @Override
    public SerieSessao salvarSerieSessao(SerieSessaoDTO serieSessaoDTO) {
        SerieSessao novaSerieSessao = serieSessaoRepository.save(serieSessaoMapper.toEntity(serieSessaoDTO));
        SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO = new SessaoProgressaoResponseDTO();
        //cria o dto que a gerencia conquista recebe, nao ta funcionando
        UUID alunoId = UUID.fromString("3f6b6938-5cd3-415b-b2d2-bdbf46420f30");
        sessaoProgressaoResponseDTO.setPeso(serieSessaoDTO.getPeso());
        sessaoProgressaoResponseDTO.setAlunoId(alunoId);
        sessaoProgressaoResponseDTO.setNumeroDeRepeticoes(serieSessaoDTO.getNumeroDeRepeticoes());
        sessaoProgressaoResponseDTO.setNomeExercicio("supininho maroto");
        //recebe o dto
        gerenciaConquistas.decisaoConquista(sessaoProgressaoResponseDTO);
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


}
