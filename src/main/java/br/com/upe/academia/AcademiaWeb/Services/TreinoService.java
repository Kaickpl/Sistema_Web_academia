package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TreinoService {
    public Treino criarTreino(Treino treino);
    public Treino atualizarTreino(Treino treino);
    public void deletarTreino(UUID idTreino);
    public Treino buscarTreino(UUID id);
    public Treino deepCopyTreino(Treino treinoOriginal);
    public void restaurarRegras(Treino novoTreino, List<TreinoExercicio> regrasAntigas);
}

