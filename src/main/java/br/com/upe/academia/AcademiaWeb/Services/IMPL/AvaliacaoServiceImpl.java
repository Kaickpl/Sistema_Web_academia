package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.*;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.AvaliacaoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.AvaliacaoService;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private MedidasCorporaisService medidasCorporaisService;


    @Override
    public Avaliacao criarAvaliacao(AvaliacaoDTOs avaliacaoDTOs) {
        Aluno aluno = alunoService.buscarAlunoPorId(avaliacaoDTOs.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        Optional<Personal> personal = personalService.buscarPersonalEmail(avaliacaoDTOs.getEmailPersonal());
        if (personal.isEmpty()){
            throw new InformacaoNaoEncontradoException("Não existe um personal registrado com esse CREF");
        }
        MedidasCorporais medidasCorporais = medidasCorporaisService.buscarMedidasPorId(avaliacaoDTOs.getMedidasId());
        if (medidasCorporais == null){
            throw new InformacaoNaoEncontradoException("Não foram encontradas medidas corporais com esse id.");
        }
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setAluno(aluno);
        novaAvaliacao.setPersonal(personal.get());
        novaAvaliacao.setMedidasCorporais(medidasCorporais);
        novaAvaliacao.setObjetivoAvaliacao(avaliacaoDTOs.getObjetivoAvaliacao());
        novaAvaliacao.setDataAvaliacao(avaliacaoDTOs.getDataAvaliacao());
        novaAvaliacao.setDataSolicitacao(avaliacaoDTOs.getDataSolicitacao());
        return avaliacaoRepository.save(novaAvaliacao);
    }

    @Override
    public List<AvaliacaoResponseDTO> mostrarAvaliacaoAluno(UUID alunoId) {
        Aluno aluno = alunoService.buscarAlunoPorId(alunoId);
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        boolean existeAvaliacao = avaliacaoRepository.existsByAluno_IdUsuario(alunoId);
        if (!existeAvaliacao){
            throw new InformacaoNaoEncontradoException("Este aluno não possui nenhuma avaliação registrada");
        }
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAluno_IdUsuario(alunoId);
        return avaliacoes.stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AvaliacaoResponseDTO> mostrarAvaliacaoPersonal(String cref) {
        Personal personal = personalService.buscarPersonal(cref);
        if (personal == null){
            throw new UsuarioNaoEncontradoException();
        }
        boolean existeAvaliacao = avaliacaoRepository.existsByPersonal_Cref(cref);
        if (!existeAvaliacao){
            throw new InformacaoNaoEncontradoException("Este personal não possui nenhuma avaliação registrada");
        }
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByPersonal_Cref(cref);
        return avaliacoes.stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AvaliacaoResponseDTO> mostrarAvaliacaoPersonalEData(String cref, LocalDate data) {
        boolean existeAvaliacao = avaliacaoRepository.existsByPersonal_Cref(cref);
        if (!existeAvaliacao){
            throw new InformacaoNaoEncontradoException("Este personal não possui nenhuma avaliação registrada");
        }
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByPersonal_CrefAndDataAvaliacao(cref,data);
        return avaliacoes.stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());
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
    public Avaliacao alterarDataAvaliacao(UUID idAvaliacao, ModificarDataAvaliacaoDTO modificarDataAvaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoRepository.findByIdAvaliacao(idAvaliacao);
        if (avaliacao == null){
            throw new InformacaoNaoEncontradoException("Não foi encontrada uma avaliação com esse ID");
        }
        if (modificarDataAvaliacaoDTO.getDataAvaliacao() == null){
            throw new ValorInvalidoException("Data de avaliação inválida");
        }
        avaliacao.setDataAvaliacao(modificarDataAvaliacaoDTO.getDataAvaliacao());
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public Avaliacao alterarPersonal(UUID idAvaliacao, ModificarPersonalAvaliacaoDTO avaliacaoDTOs) {
        Avaliacao avaliacao = avaliacaoRepository.findByIdAvaliacao(idAvaliacao);

        if (avaliacao == null){
            throw new InformacaoNaoEncontradoException("Não foi encontrada uma avaliação com esse ID");
        }

        Optional<Personal> personalOptional = personalService.buscarPersonalEmail(avaliacaoDTOs.getEmail());

        if (personalOptional.isEmpty()){
            throw new InformacaoNaoEncontradoException("Não foi encontrado um personal com o email: " + avaliacaoDTOs.getEmail());
        }

        Personal personalEncontrado = personalOptional.get();

        avaliacao.setPersonal(personalEncontrado);

        return avaliacaoRepository.save(avaliacao);
    }
}
