package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExecutavelDeletarTreinoSessao implements Executavel {
    private TreinoSessaoService treinoSessaoService;
    private TreinoSessao treinoSessaoDeletada;
    private UUID idTreinoSessao;
    private List<ExercicioSessao> exerciciosSalvos;

    public ExecutavelDeletarTreinoSessao(TreinoSessaoService treinoSessaoService, UUID idTreinoSessao) {
        this.treinoSessaoService = treinoSessaoService;
        this.idTreinoSessao = idTreinoSessao;
    }

    @Override
    public void executar() {
        UUID idParaBuscar = (this.treinoSessaoDeletada == null) ? this.idTreinoSessao : this.treinoSessaoDeletada.getIdTreinoSessao();

        this.treinoSessaoDeletada = treinoSessaoService.buscarSessaoPorId(idParaBuscar);

        if(this.treinoSessaoDeletada != null){
            this.exerciciosSalvos = clonarExerciciosRecursivamente(this.treinoSessaoDeletada.getExerciciosRealizados());
            treinoSessaoService.apagarTreinoSessao(idParaBuscar);
        }
    }

    @Override
    public void desfazer() {
        if(this.treinoSessaoDeletada != null){
            this.treinoSessaoDeletada.setExerciciosRealizados(new ArrayList<>());
            this.treinoSessaoDeletada.setIdTreinoSessao(null);
            this.treinoSessaoDeletada = this.treinoSessaoService.salvarEntidade(this.treinoSessaoDeletada);

            if(this.exerciciosSalvos != null && !this.exerciciosSalvos.isEmpty()){
                this.treinoSessaoService.restaurarExecucaoCompleta(this.treinoSessaoDeletada, this.exerciciosSalvos);
            }
        }

        }

    private List<ExercicioSessao> clonarExerciciosRecursivamente(List<ExercicioSessao> originais) {
        if (originais == null) return new ArrayList<>();

        return originais.stream().map(original -> {
            ExercicioSessao copia = new ExercicioSessao();
            // Copia dados
            copia.setExercicioTemplate(original.getExercicioTemplate());
            // Copia netos (Séries)
            copia.setSeriesRealizadas(clonarSeries(original.getSeriesRealizadas()));

            return copia;
        }).collect(Collectors.toList());
    }

    private List<SerieSessao> clonarSeries(List<SerieSessao> originais) {
        if (originais == null) return new ArrayList<>();

        return originais.stream().map(original -> {
            SerieSessao copia = new SerieSessao();
            copia.setPeso(original.getPeso());
            copia.setNumeroDeRepeticoes(original.getNumeroDeRepeticoes());
            return copia;
        }).collect(Collectors.toList());
    }


}
