package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosDTO;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ObjetivosRepository;
import br.com.upe.academia.AcademiaWeb.Services.ObjetivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ObjetivosServiceImpl implements ObjetivosService {
    @Autowired
    ObjetivosRepository objetivosRepository;

    @Autowired
    AlunoRepository alunoRepository;
    private Objetivos objetivos;

    @Override
    public Objetivos registrarObjetivo(ObjetivosDTO objetivosDto) {
        Aluno aluno = alunoRepository.findByIdUsuario(objetivosDto.getAlunoId());
        if (aluno == null){
            throw new UsuarioNaoEncontradoException();
        }
        Objetivos objetivos = new Objetivos();
        objetivos.setAluno(aluno);
        objetivos.setCondicao(objetivos.getCondicao());
        objetivos.setStatus(objetivos.getStatus());
        objetivos.setValorAlvo(objetivos);
        return null;
    }

    @Override
    public List<ObjetivosDTO> mostrarTodosObjetivos(UUID alunoId) {
        return List.of();
    }

    @Override
    public List<ObjetivosDTO> mostrarObjetivosNaoConcluidos(UUID alunoId) {
        return List.of();
    }

    @Override
    public List<ObjetivosDTO> mostrarObjetivosConcluidos(UUID alunoId) {
        return List.of();
    }

    @Override
    public Objetivos atualizaObjetivo(ObjetivosDTO objetivosDto) {
        return null;
    }
}
