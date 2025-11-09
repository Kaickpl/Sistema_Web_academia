package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import lombok.Getter;

@Getter
public class ExecutavelCriarTreino implements Executavel{
    private TreinoService treinoService;
    private Treino treino;
    private Treino treinoCriado;

    public ExecutavelCriarTreino(TreinoService service, Treino treino) {
        this.treinoService = service;
        this.treino = treino;
    }

    @Override
    public void executar() {
        if (this.treino.getIdTreino() != null) {
            this.treino.setIdTreino(null);
        }
        this.treinoCriado = treinoService.criarTreino(treino);
    }

    @Override
    public void desfazer() {
        if(this.treinoCriado != null){
            treinoService.deletarTreino(treinoCriado.getIdTreino());
        }
    }
}
