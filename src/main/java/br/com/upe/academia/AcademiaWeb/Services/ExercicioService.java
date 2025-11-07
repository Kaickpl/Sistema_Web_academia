package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public interface ExercicioService {
    public Exercicio buscarExercicio(UUID idExercicio);
    public List<Exercicio> buscarExercicioPorTreino(UUID idTreino);
    public Exercicio adicionarExercicio(Exercicio exercicio);
    public Exercicio alterarExercicio(Exercicio exercicio);
    public void removerExercicio(UUID idExercicio);
    public void restaurarLigacoesTreino(UUID idExercicio, List<Treino> treinosParaReligar);
}
