package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ObjetivosRepository;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import org.hibernate.type.descriptor.java.ObjectJavaType;
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
    AlunoRepository alunoRepository;

    @Autowired
    ObjetivosRepository objetivosRepository;

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
        Aluno aluno = alunoRepository.findByIdUsuario(medidasCorporaisDTOs.getAlunoId());
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
            Double novoValor = getValorMedida(medidasCorporais, tipo);

            if (novoValor != null && novoValor > 0){
                List<Objetivos> objetivosParaAtualizar = objetivosRepository.findAllByAluno_IdUsuarioAndTipoMedidaAndConcluido(alunoId, tipo, false);
                for (Objetivos objetivo : objetivosParaAtualizar){
                    boolean ehPraDiminuir = objetivo.getValorAlvo() < objetivo.getValorAtual();
                    objetivo.setValorAtual(novoValor);
                    boolean concluido;
                    if (ehPraDiminuir){
                        concluido = novoValor <= objetivo.getValorAlvo();
                    } else {
                        concluido = novoValor >= objetivo.getValorAlvo();
                    }
                    objetivo.setConcluido(concluido);
                    objetivosRepository.save(objetivo);
                }
            }
        }
    }
    private Double getValorMedida(MedidasCorporais medidas, String tipo) {
        switch (tipo.toLowerCase()) {
            case "peso": return medidas.getPeso();
            case "braco": return medidas.getBraco();
            case "abdomen": return medidas.getAbdomen();
            case "cintura": return medidas.getCintura();
            case "peito": return medidas.getPeito();
            case "quadril": return medidas.getQuadril();
            case "coxa": return medidas.getCoxa();
            case "ombro": return medidas.getOmbro();
            case "massaMagra": return medidas.getMassaMagra();
            case "gordura": return medidas.getGordura();
            case "percentualAgua": return medidas.getPercentualAgua();
            case "altura": return medidas.getAltura();
            default: return null;
        }
}}
