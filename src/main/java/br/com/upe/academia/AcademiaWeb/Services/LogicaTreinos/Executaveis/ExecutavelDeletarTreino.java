package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class ExecutavelDeletarTreino implements Executavel {
    private Treino treinoDeletado;
    private UUID id;
    private TreinoService treinoService;

    public ExecutavelDeletarTreino(TreinoService service, UUID id) {
        this.id = id;
        this.treinoService = service;
    }

    @Override
    public void executar() {
        this.treinoDeletado = treinoService.buscarTreino(id);
        treinoService.deletarTreino(id);
    }

    @Override
    public void desfazer() {
        if (this.treinoDeletado != null) {
            this.treinoService.criarTreino(treinoDeletado);
        }
    }
}