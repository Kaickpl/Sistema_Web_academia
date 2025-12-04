package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface MoedasService {
    Aluno adicionarMoedas(UUID alunoId, int quantidade);
}
