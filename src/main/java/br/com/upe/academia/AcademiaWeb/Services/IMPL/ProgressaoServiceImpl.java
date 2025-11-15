package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.Contexto;
import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ProgressaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProgressaoServiceImpl implements ProgressaoService{

    @Autowired
    ProgressaoRepository progressaoRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    GerenciaConquistas gerenciaConquistas;


    @Override
    public Progressao salvaCarga(ProgressaoDTOs progressaoDTOs) {
        Aluno aluno = alunoRepository.findByIdUsuario(progressaoDTOs.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        if (progressaoDTOs.getPeso() <= 0){
            throw new ValorInvalidoException("O peso deve ser maior que zero.");
        }
        ///gerenciaConquistas.decisaoConquista(progressaoDTOs);
        Progressao novaProgressao = new Progressao(aluno, progressaoDTOs.getNomeExercicio(), progressaoDTOs.getPeso(), progressaoDTOs.getRepeticoes());

        return progressaoRepository.save(novaProgressao);
    }

    @Override
    public List<ProgressaoResponseDTOs> getHistoricoCarga(UUID alunoId, String nomeExercicio) {
        if (alunoRepository.findByIdUsuario(alunoId) == null){
            throw new UsuarioNaoEncontradoException();
        }

        List<Progressao> progressoes = progressaoRepository
                .findByAluno_IdUsuarioAndNomeExercicioOrderByDataAsc(alunoId, nomeExercicio);

        return progressoes.stream()
                .map(ProgressaoResponseDTOs::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProgressaoResponseDTOs getUltimaCarga(UUID alunoId, String nomeExercicio) {
        Progressao ultimoRegistro = progressaoRepository.findTop1ByAluno_IdUsuarioAndNomeExercicioOrderByDataDesc(alunoId, nomeExercicio);
        if (ultimoRegistro == null){
            throw new InformacaoNaoEncontradoException("Aluno com Id: " + alunoId + "não tem nenhum registro de carga no exercício: " + nomeExercicio);
        }
        return new ProgressaoResponseDTOs(ultimoRegistro);
    }

    @Override
    public Progressao alterarProgressao(UUID id, ProgressaoDTOs progressaoDTOs) {
        Optional<Progressao> progressaoExiste = progressaoRepository.findById(id);
        int peso = progressaoDTOs.getPeso();
        int repeticoes = progressaoDTOs.getRepeticoes();
        if (progressaoExiste.isEmpty()){
            throw new InformacaoNaoEncontradoException("Não foi encontrada uma progressão com esse id");
        }
        Progressao progressao = progressaoExiste.get();
        if (peso <= 0){
            throw new ValorInvalidoException("O peso deve ser maior que zero");
        }
        if (repeticoes <= 0){
            throw new ValorInvalidoException("As repetições dever ser mais que zero");
        }
        progressao.setNomeExercicio(progressaoDTOs.getNomeExercicio());
        progressao.setPeso(progressaoDTOs.getPeso());
        progressao.setRepeticoes(progressaoDTOs.getRepeticoes());

        return progressaoRepository.save(progressao);
    }

    @Override
    public void deletarProgressao(UUID id) {
        if (!progressaoRepository.existsById(id)){
            throw new InformacaoNaoEncontradoException("Não foi encontrada uma progressão com esse id");
        }
        progressaoRepository.deleteById(id);
    }
}
