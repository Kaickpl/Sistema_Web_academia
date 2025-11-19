package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ObjetivosRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import br.com.upe.academia.AcademiaWeb.Services.ObjetivosService;
import br.com.upe.academia.AcademiaWeb.utils.MedidasUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedidasCorporaisServiceImpl implements MedidasCorporaisService {
    @Autowired
    MedidasCorporaisRepository medidasCorporaisRepository;
    @Autowired
    private GerenciaConquistas gerenciaConquistas;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private ObjetivosService objetivosService;

    @Override
    public List<MedidasCorporaisResponseDTO> mostrarHistoricoMedidasCorporais(UUID alunoId) {
        List<MedidasCorporais> medidasCorporaisList = medidasCorporaisRepository.findByAluno_IdUsuarioOrderByDataAsc(alunoId);

        return medidasCorporaisList.stream().map(MedidasCorporaisResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public MedidasCorporaisResponseDTO mostrarMedidasAtuais(UUID alunoId) {
        MedidasCorporais medidasCorporaisAtuais = medidasCorporaisRepository
                .findTop1ByAluno_IdUsuarioOrderByDataDesc(alunoId);
        if (medidasCorporaisAtuais == null){
            throw new InformacaoNaoEncontradoException("Aluno com Id: " + alunoId + " não possui medidas registradas.");
        }
        return new MedidasCorporaisResponseDTO(medidasCorporaisAtuais);
    }

    @Override
    public MedidasCorporais registrarMedidas(MedidasCorporaisRegistroDTO medidasCorporaisDTOs) {
        Aluno aluno = alunoService.buscarAlunoPorId(medidasCorporaisDTOs.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        validarMedida(medidasCorporaisDTOs.getBraco(), "braço");
        validarMedida(medidasCorporaisDTOs.getAbdomen(), "abdômen");
        validarMedida(medidasCorporaisDTOs.getCintura(), "cintura");
        validarMedida(medidasCorporaisDTOs.getPeito(), "peito");
        validarMedida(medidasCorporaisDTOs.getQuadril(), "quadril");
        validarMedida(medidasCorporaisDTOs.getCoxa(), "coxa");
        validarMedida(medidasCorporaisDTOs.getOmbro(), "ombro");
        validarMedida(medidasCorporaisDTOs.getMassaMagra(), "massa magra");
        validarMedida(medidasCorporaisDTOs.getGordura(), "percentual de gordura");
        validarMedida(medidasCorporaisDTOs.getPercentualAgua(), "percentual de água");
        validarMedida(medidasCorporaisDTOs.getPeso(), "peso");
        validarMedida(medidasCorporaisDTOs.getAltura(), "altura");

        MedidasCorporais novasMedidas = new MedidasCorporais();
        novasMedidas.setAluno(aluno);
        novasMedidas.setBraco(medidasCorporaisDTOs.getBraco());
        novasMedidas.setAbdomen(medidasCorporaisDTOs.getAbdomen());
        novasMedidas.setCintura(medidasCorporaisDTOs.getCintura());
        novasMedidas.setPeito(medidasCorporaisDTOs.getPeito());
        novasMedidas.setQuadril(medidasCorporaisDTOs.getQuadril());
        novasMedidas.setCoxa(medidasCorporaisDTOs.getCoxa());
        novasMedidas.setOmbro(medidasCorporaisDTOs.getOmbro());
        novasMedidas.setMassaMagra(medidasCorporaisDTOs.getMassaMagra());
        novasMedidas.setGordura(medidasCorporaisDTOs.getGordura());
        novasMedidas.setPercentualAgua(medidasCorporaisDTOs.getPercentualAgua());
        novasMedidas.setPeso(medidasCorporaisDTOs.getPeso());
        novasMedidas.setAltura(medidasCorporaisDTOs.getAltura());
        MedidasCorporais medidasSalvas = medidasCorporaisRepository.save(novasMedidas);
        atualizarObjetivosAluno(novasMedidas);
        return medidasSalvas;
    }

    public void validarMedida(Double valor, String nomeCampo){
        if (valor <= 0){
            throw new ValorInvalidoException("A medida de " + nomeCampo + " deve ser maior que zero");
        }
    }

    public void atualizarObjetivosAluno(MedidasCorporais medidasCorporais){
        UUID alunoId = medidasCorporais.getAluno().getIdUsuario();
        List<String> tiposDeMedida = List.of("peso", "braco", "cintura", "abdomen", "peito", "quadril", "coxa", "ombro", "massaMagra", "gordura", "percentualAgua", "altura");
        for (String tipo : tiposDeMedida) {
            Double novoValor = MedidasUtils.getValorPorNome(medidasCorporais, tipo);

            if (novoValor != null && novoValor > 0){
                List<ObjetivosDTO> objetivosParaAtualizar = objetivosService.mostrarObjetivosNaoConcluidosPorTipoMedida(alunoId, tipo);
                for (ObjetivosDTO objetivo : objetivosParaAtualizar){
                    boolean estavaConcluidoAntes = objetivo.isConcluido();
                    boolean ehPraDiminuir = objetivo.getValorAlvo() < objetivo.getValorAtual();
                    objetivo.setValorAtual(novoValor);
                    boolean concluido;
                    if (ehPraDiminuir){
                        concluido = novoValor <= objetivo.getValorAlvo();
                    } else {
                        concluido = novoValor >= objetivo.getValorAlvo();
                    }
                    objetivo.setConcluido(concluido);
                    ObjetivoRegistroDTO objetivoRegistroDTO = new ObjetivoRegistroDTO();
                    objetivoRegistroDTO.setAlunoId(objetivo.getAlunoId());
                    objetivoRegistroDTO.setTipoMedida(objetivo.getTipoMedida());
                    objetivoRegistroDTO.setValorAlvo(objetivo.getValorAlvo());
                    objetivoRegistroDTO.setValorAtual(objetivo.getValorAtual());
                    objetivoRegistroDTO.setConcluido(objetivo.isConcluido());
                    objetivosService.atualizaObjetivo(objetivo.getIdObjetivo(), objetivoRegistroDTO);
                    if (!estavaConcluidoAntes && concluido) {
                        gerenciaConquistas.decisaoConquistaObjetivo(alunoId);
                    }
                }
            }
        }
    }

    @Override
    public Double buscarUltimoValorMedida(UUID alunoId, String tipoMedida) {
        MedidasCorporais ultimaMedida = medidasCorporaisRepository.findTop1ByAluno_IdUsuarioOrderByDataDesc(alunoId);

        if (ultimaMedida == null) {
            throw new InformacaoNaoEncontradoException("Aluno com Id: " + alunoId + "não possui medidas registradas");
        }
        return MedidasUtils.getValorPorNome(ultimaMedida, tipoMedida);
    }
}
