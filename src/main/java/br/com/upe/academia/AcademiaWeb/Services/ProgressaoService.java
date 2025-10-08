package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProgressaoService {
    public Progressao salvaCarga(UUID AlunoId, String exercicio, int peso);
    // public Progressao mostrarProgressao();
}
