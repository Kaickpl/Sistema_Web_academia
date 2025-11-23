package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExecutavelDeletarExercicio implements Executavel{
    private ExercicioService exercicioService;
    private UUID idOriginal;
    private Exercicio exercicioRemovido;
    private List<TreinoExercicio> regrasAntigas;

    public ExecutavelDeletarExercicio(ExercicioService exercicioService, UUID idExercicio) {
        this.exercicioService = exercicioService;
        this.idOriginal = idExercicio;
    }

    @Override
    public void executar() {
        UUID idParaBuscar = (this.exercicioRemovido == null) ? this.idOriginal : this.exercicioRemovido.getIdExercicio();

        this.exercicioRemovido = this.exercicioService.buscarExercicio(idParaBuscar);

        if(this.exercicioRemovido != null){
            if(this.exercicioRemovido.getRegrasDeTreinos() != null){
                this.exercicioRemovido.getRegrasDeTreinos().size();
                this.regrasAntigas = new ArrayList<>(this.exercicioRemovido.getRegrasDeTreinos());
            }

            exercicioService.removerExercicio(idParaBuscar);


        }
    }



    @Override
    public void desfazer() {
        if(this.exercicioRemovido != null){
            this.exercicioRemovido.setRegrasDeTreinos(new ArrayList<>());
            this.exercicioRemovido.setExerciciosExecucao(new ArrayList<>());
            this.exercicioRemovido.setIdExercicio(null);
            this.exercicioRemovido = this.exercicioService.adicionarExercicio(this.exercicioRemovido);

            if(this.regrasAntigas != null && !this.regrasAntigas.isEmpty()){
                exercicioService.restaurarLigacoesRegras(this.exercicioRemovido, this.regrasAntigas);
            }
        }
    }
}
