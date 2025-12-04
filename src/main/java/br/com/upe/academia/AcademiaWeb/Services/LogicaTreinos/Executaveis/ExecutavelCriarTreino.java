package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ExecutavelCriarTreino implements Executavel{
    private AlunoService alunoService;
    private TreinoService treinoService;
    private Treino treinoOriginal;
    private Treino treinoCriado;
    private UUID idAluno;

    public ExecutavelCriarTreino(TreinoService service, AlunoService alunoService, Treino treino, UUID idAluno) {
        this.treinoService = service;
        this.treinoOriginal = treino;
        this.alunoService = alunoService;
        this.idAluno = idAluno;
        this.treinoService = treinoService;
    }

    @Override
    public void executar() {
        Aluno alunoDonoDoTreino = alunoService.buscarAlunoPorId(idAluno);
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

        alunoService.atribuirTreinoAluno(idAluno ,treinoCriado.getIdTreino(), false);

    }

    @Override
    public void desfazer() {
        if(this.treinoCriado != null){
            treinoService.deletarTreino(treinoCriado.getIdTreino());
        }
    }
}
