package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ConquistasService {
    public Conquistas registrarConquista(UUID alunoId, String titulo, String descricao, int moedas);
    public List<Conquistas> mostrarConquistas(UUID alunoId);
}
