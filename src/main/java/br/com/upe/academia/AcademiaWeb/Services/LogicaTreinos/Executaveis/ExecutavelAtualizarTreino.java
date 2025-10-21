package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService; // Serviço CORRETO
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.Executavel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutavelAtualizarTreino implements Executavel {

    private final TreinoService treinoService;

    private final Treino dadosNovos;

    private Treino treinoAntigo;

    private Treino treinoAtualizado;

    public ExecutavelAtualizarTreino(TreinoService treinoService, UUID idTreino, Treino dadosNovos) {
        this.treinoService = treinoService;
        dadosNovos.setIdTreino(idTreino);
        this.dadosNovos = dadosNovos;
    }

    @Override
    public void executar() {
        this.treinoAntigo = this.treinoService.buscarTreino(this.dadosNovos.getIdTreino());
        this.treinoAtualizado = this.treinoService.atualizarTreino(this.dadosNovos);
    }

    @Override
    public void desfazer() {
        if (this.treinoAntigo != null) {
            this.treinoAtualizado = this.treinoService.atualizarTreino(this.treinoAntigo);
        }
    }
}