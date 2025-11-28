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

    private UUID idTreino;

    private Treino treinoAntigo;

    private Treino treinoAtualizado;

    public ExecutavelAtualizarTreino(TreinoService treinoService, UUID idTreino, Treino dadosNovos) {
        this.treinoService = treinoService;
        this.idTreino = idTreino;
        dadosNovos.setIdTreino(idTreino);
        this.dadosNovos = dadosNovos;
    }

    @Override
    public void executar() {
        Treino estadoAtual = this.treinoService.buscarTreino(this.idTreino);
        this.treinoAntigo = new Treino();
        this.treinoAntigo.setIdTreino(estadoAtual.getIdTreino());
        this.treinoAntigo.setNome(estadoAtual.getNome());

        this.treinoAtualizado = treinoService.atualizarTreino(dadosNovos);
    }

    @Override
    public void desfazer() {
        if (this.treinoAntigo != null) {
            this.treinoAtualizado = this.treinoService.atualizarTreino(this.treinoAntigo);
        }
    }
}