package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExecutavelDeletarTreino implements Executavel {
    private AlunoService alunoService;
    private TreinoService treinoService;
    private Treino treinoDeletado;
    private UUID idOriginal;
    private List<TreinoExercicio> regrasSalvas;
    private List<UUID> idAlunosVinculados;

    public ExecutavelDeletarTreino(TreinoService treinoService, AlunoService alunoService, UUID id) {
        this.idOriginal = id;
        this.treinoService = treinoService;
        this.alunoService = alunoService;
    }

    @Override
    public void executar() {
        UUID idParaBuscar = (this.treinoDeletado == null) ? this.idOriginal : this.treinoDeletado.getIdTreino();

        this.treinoDeletado = treinoService.buscarTreino(idParaBuscar);

        if (this.treinoDeletado != null) {
            if (this.treinoDeletado.getRegrasDeExercicios() != null) {
                this.regrasSalvas = new ArrayList<>(this.treinoDeletado.getRegrasDeExercicios());
            }
            else {
                this.regrasSalvas = new ArrayList<>();
            }

            this.idAlunosVinculados = alunoService.buscarIdAlunoPorTreino(idParaBuscar);
            treinoService.deletarTreino(idParaBuscar);
        }
    }

    @Override
    public void desfazer() {
        if (this.treinoDeletado != null) {

            this.treinoDeletado.setRegrasDeExercicios(new ArrayList<>());
            this.treinoDeletado.setTreinoExecucoes(new ArrayList<>());

            this.treinoDeletado.setIdTreino(null);

            this.treinoDeletado = this.treinoService.criarTreino(this.treinoDeletado);

            if (this.regrasSalvas != null && !this.regrasSalvas.isEmpty()) {
                this.treinoService.restaurarRegras(this.treinoDeletado, this.regrasSalvas);
            }

            if(this.idAlunosVinculados != null && !this.idAlunosVinculados.isEmpty()){
                for(UUID idAluno: this.idAlunosVinculados){
                    alunoService.atribuirTreinoAluno(idAluno, this.treinoDeletado.getIdTreino(), false);
                }
            }
        }
    }
}