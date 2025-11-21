package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;

import java.util.Optional;
import java.util.UUID;

public interface TreinoService {
    public Treino criarTreino(Treino treino);
    public Treino atualizarTreino(Treino treino);
    public void deletarTreino(UUID idTreino);
    public Treino buscarTreino(UUID id);
    public Treino deepCopyTreino(Treino treinoOriginal);
}

