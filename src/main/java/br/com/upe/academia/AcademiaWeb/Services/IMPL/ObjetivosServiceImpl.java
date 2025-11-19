package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.GerenciaConquistas;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivoRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.MedidasCorporaisRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ObjetivosRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.MedidasCorporaisService;
import br.com.upe.academia.AcademiaWeb.Services.ObjetivosService;
import br.com.upe.academia.AcademiaWeb.utils.MedidasUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ObjetivosServiceImpl implements ObjetivosService {
    @Autowired
    ObjetivosRepository objetivosRepository;

    @Autowired
    GerenciaConquistas gerenciaConquistas;

    @Autowired
    private MedidasCorporaisRepository medidasCorporaisRepository;

    @Autowired
    private AlunoService alunoService;

    @Override
    public Objetivos registrarObjetivo(ObjetivoRegistroDTO objetivosDto) {
        Aluno aluno = alunoService.buscarAlunoPorId(objetivosDto.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        MedidasCorporais ultimasMedidas = medidasCorporaisRepository.findTop1ByAluno_IdUsuarioOrderByDataDesc(aluno.getIdUsuario());

        Double valorAtual = MedidasUtils.getValorPorNome(ultimasMedidas, objetivosDto.getTipoMedida());

        if (valorAtual == null) {
            throw new InformacaoNaoEncontradoException("Não há medidas corporais registradas para o tipo: " + objetivosDto.getTipoMedida());
        }
        Objetivos objetivos = new Objetivos();
        objetivos.setAluno(aluno);
        objetivos.setValorAtual(valorAtual);
        objetivos.setValorAlvo(objetivosDto.getValorAlvo());
        objetivos.setTipoMedida(objetivosDto.getTipoMedida());
        boolean ehPraDiminuir = objetivosDto.getValorAlvo() < objetivosDto.getValorAtual();
        boolean concluido;
        if (ehPraDiminuir){
            concluido = objetivosDto.getValorAtual() <= objetivosDto.getValorAlvo();
        } else {
            concluido = objetivosDto.getValorAtual() >= objetivosDto.getValorAlvo();
        }
        objetivos.setConcluido(concluido);
        Objetivos objetivoSalvo = objetivosRepository.save(objetivos);
        if (concluido) {
            gerenciaConquistas.decisaoConquistaObjetivo(aluno.getIdUsuario());
        }

        return objetivoSalvo;
    }

    @Override
    public List<ObjetivosResponseDTO> mostrarTodosObjetivos(UUID alunoId) {
        List<Objetivos> objetivosList = objetivosRepository.findAllByAluno_IdUsuario(alunoId);
        return objetivosList.stream().map(ObjetivosResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ObjetivosResponseDTO> mostrarObjetivosNaoConcluidos(UUID alunoId) {
        List<Objetivos> objetivosList = objetivosRepository.findAllByAluno_IdUsuarioAndConcluido(alunoId, false);
        return objetivosList.stream().map(ObjetivosResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ObjetivosResponseDTO> mostrarObjetivosConcluidos(UUID alunoId) {
        List<Objetivos> objetivosList = objetivosRepository.findAllByAluno_IdUsuarioAndConcluido(alunoId, true);
        return objetivosList.stream().map(ObjetivosResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ObjetivosDTO> mostrarObjetivosNaoConcluidosPorTipoMedida(UUID alunoId, String tipo) {
        List<Objetivos> objetivosList = objetivosRepository.findAllByAluno_IdUsuarioAndTipoMedidaAndConcluido(alunoId, tipo, false);
        return objetivosList.stream().map(ObjetivosDTO::new).collect(Collectors.toList());
    }

    @Override
    public Objetivos atualizaObjetivo(UUID id, ObjetivoRegistroDTO objetivosDto) {
        Optional<Objetivos> objetivoExiste = objetivosRepository.findById(id);
        Aluno aluno = alunoService.buscarAlunoPorId(objetivosDto.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        if (objetivoExiste.isEmpty()){
            throw new InformacaoNaoEncontradoException("Não foi encontrada uma progressão com esse id");
        }
        Objetivos objetivos = objetivoExiste.get();
        boolean estavaConcluidoAntes = objetivos.isConcluido();
        objetivos.setTipoMedida(objetivosDto.getTipoMedida());
        objetivos.setAluno(aluno);
        objetivos.setValorAtual(objetivosDto.getValorAtual());
        objetivos.setValorAlvo(objetivosDto.getValorAlvo());
        boolean ehPraDiminuir = objetivos.getValorAtual() > objetivos.getValorAlvo();
        boolean concluido;
        if (ehPraDiminuir){
            concluido = objetivosDto.getValorAtual() <= objetivosDto.getValorAlvo();
        } else {
            concluido = objetivosDto.getValorAtual() >= objetivosDto.getValorAlvo();
        }
        objetivos.setConcluido(concluido);
        Objetivos objetivoAtualizado = objetivosRepository.save(objetivos);
        if (!estavaConcluidoAntes && concluido) {
            gerenciaConquistas.decisaoConquistaObjetivo(aluno.getIdUsuario());
        }

        return objetivoAtualizado;
    }

    @Override
    public void deletarObjetivo(UUID id) {
        if (!objetivosRepository.existsByIdObjetivo(id)){
            throw new InformacaoNaoEncontradoException("Nenhum objetivo com esse id foi encontrado");
        }
        objetivosRepository.deleteById(id);
    }


}
