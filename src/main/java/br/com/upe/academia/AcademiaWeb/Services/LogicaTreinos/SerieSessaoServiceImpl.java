package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Repositories.SerieSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import br.com.upe.academia.AcademiaWeb.utils.SessaoProgressaoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SerieSessaoServiceImpl implements SerieSessaoService {

    @Autowired
    private SerieSessaoRepository serieSessaoRepository;

    @Autowired
    private SerieSessaoMapper serieSessaoMapper;

    @Autowired
    private SessaoProgressaoResponseMapper  sessaoProgressaoResponseMapper;

    @Autowired
    private GerenciaConquistas gerenciaConquistas;

    @Override
    public SerieSessao buscarSerieSessao(UUID idSerieSessao) {
        return serieSessaoRepository.findById(idSerieSessao).orElseThrow(() -> new RuntimeException("Série de Sessão não encontrada."));
    }

    @Override
    public SerieSessao salvarSerieSessao(SerieSessaoDTO serieSessaoDTO) {
        SessaoProgressaoResponseDTO sessaoProgressaoResponseDTO = new SessaoProgressaoResponseDTO(serieSessaoDTO.getIdSerieSessao());
        gerenciaConquistas.decisaoConquista(sessaoProgressaoResponseDTO);
        return serieSessaoRepository.save(serieSessaoMapper.toEntity(serieSessaoDTO));
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
    public SessaoProgressaoResponseDTO buscarRegistroProgressao(UUID idSerieSessao) {
        return sessaoProgressaoResponseMapper.toDTO(idSerieSessao);
    }

}
