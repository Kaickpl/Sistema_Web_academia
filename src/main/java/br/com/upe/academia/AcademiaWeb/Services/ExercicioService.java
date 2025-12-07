package br.com.upe.academia.AcademiaWeb.Services;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ExercicioService {
    public Exercicio buscarExercicio(UUID idExercicio);
    public List<Exercicio> buscarExercicioPorTreino(UUID idTreino);
    public List<Exercicio> buscarTodosOsExercicios();
    public Exercicio adicionarExercicio(Exercicio exercicio);
    public Exercicio alterarExercicio(Exercicio exercicio);
    public void removerExercicio(UUID idExercicio);
    public void restaurarLigacoesRegras(Exercicio exercicioNovo, List<TreinoExercicio> regrasParaReligar);
}
