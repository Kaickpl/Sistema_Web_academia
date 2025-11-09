package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExecutavelDeletarExercicio implements Executavel{
    private ExercicioService exercicioService;
    private UUID idOriginal;
    private Exercicio exercicioRemovido;
    private List<Treino> treinosAntigos;

    public ExecutavelDeletarExercicio(ExercicioService exercicioService, UUID idExercicio) {
        this.exercicioService = exercicioService;
        this.idOriginal = idExercicio;
    }

    @Override
    public void executar() {
        UUID idParaBuscar;

        if (this.exercicioRemovido == null) {
            idParaBuscar = this.idOriginal;
        } else {
            idParaBuscar = this.exercicioRemovido.getIdExercicio();
        }

        this.exercicioRemovido = this.exercicioService.buscarExercicio(idParaBuscar);

        if (exercicioRemovido != null) {
            this.treinosAntigos = new ArrayList<>(exercicioRemovido.getTreinos());
            exercicioService.removerExercicio(idParaBuscar);
        } else {
            throw new RuntimeException("Exercício não encontrado para deleção (ou já deletado).");
        }
    }


    @Override
    public void desfazer() {
        if (exercicioRemovido != null) {
            exercicioRemovido.setSeries(new ArrayList<>());
            exercicioRemovido.setTreinos(new ArrayList<>());
            exercicioRemovido.setIdExercicio(null);
            Exercicio novoExercicio = exercicioService.adicionarExercicio(exercicioRemovido);

            if (this.treinosAntigos != null && !this.treinosAntigos.isEmpty()) {
                exercicioService.restaurarLigacoesTreino(novoExercicio.getIdExercicio(), this.treinosAntigos);
                this.exercicioRemovido = novoExercicio;
            }
        }
    }
}
