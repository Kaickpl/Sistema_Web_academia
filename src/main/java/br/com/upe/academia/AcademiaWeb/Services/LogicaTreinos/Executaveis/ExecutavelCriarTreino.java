package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExecutavelCriarTreino implements Executavel{
    private TreinoService treinoService;
    private Treino treinoOriginal;
    private Treino treinoCriado;

    public ExecutavelCriarTreino(TreinoService service, Treino treino) {
        this.treinoService = service;
        this.treinoOriginal = treino;
    }

    @Override
    public void executar() {
        if(this.treinoOriginal.getIdTreino()!=null){
            Treino treinoLimpo = new Treino();
            treinoLimpo.setNome(this.treinoOriginal.getNome());

            if(this.treinoOriginal.getRegrasDeExercicios()!=null){
                List<TreinoExercicio> novasRegras = new ArrayList<>();

                for(TreinoExercicio regraAntiga : this.treinoOriginal.getRegrasDeExercicios()){
                    TreinoExercicio novaRegra = new TreinoExercicio();
                    novaRegra.setTempoDeDescanso(regraAntiga.getTempoDeDescanso());
                    novaRegra.setExercicioTemplate(regraAntiga.getExercicioTemplate());
                    novaRegra.setIdTreinoExercicio(regraAntiga.getIdTreinoExercicio());

                    List<Serie> novasSeries = new ArrayList<>();
                    if(regraAntiga.getIdTreinoExercicio()!=null){
                        for(Serie serieAntiga : regraAntiga.getSeriesTemplate()){
                            Serie novaSerie = new Serie();
                            novaSerie.setTreinoExercicio(novaRegra);
                            novasSeries.add(novaSerie);
                        }
                        novaRegra.setSeriesTemplate(novasSeries);
                        novasRegras.add(novaRegra);
                    }
                    treinoLimpo.setRegrasDeExercicios(novasRegras);
                }
            }
            this.treinoCriado = treinoService.criarTreino(treinoLimpo);
            this.treinoOriginal.setIdTreino(this.treinoCriado.getIdTreino());

        }
        else {
            this.treinoCriado =  treinoService.criarTreino(treinoOriginal);
        }
    }

    @Override
    public void desfazer() {
        if(this.treinoCriado != null){
            treinoService.deletarTreino(treinoCriado.getIdTreino());
        }
    }
}
