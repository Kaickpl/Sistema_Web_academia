package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import java.util.UUID;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.utils.SerieMapper;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarSerie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exerciciosTemplate/{idExercicio}/seriesTemplate")
public class SerieController{

    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private SerieService serieService;

    @Autowired
    private SerieMapper serieMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> buscarSerie(@PathVariable UUID id){
        Serie serie = serieService.buscarSerie(id);
        SerieDTO serieDTO = serieMapper.toDTO(serie);
        return ResponseEntity.ok().body(serieDTO);
    }

    //A parte do uri serve para dizer que deu certo e mostar o caminho exato para o que criou
    @PostMapping
    public ResponseEntity<SerieDTO> adicionarSerie(@PathVariable UUID idExercicio){
        ExecutavelCriarSerie comandoCriarSerie = new ExecutavelCriarSerie(serieService, idExercicio);
        commandHistory.execute(comandoCriarSerie);
        Serie novaSerie = comandoCriarSerie.getSerieCriada();
        SerieDTO serieDTO = serieMapper.toDTO(novaSerie);
        return ResponseEntity.status(HttpStatus.CREATED).body(serieDTO);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSerie(@PathVariable UUID id){
        ExecutavelDeletarSerie comandoDeletar = new ExecutavelDeletarSerie(serieService, id);
        commandHistory.execute(comandoDeletar);
        return ResponseEntity.noContent().build();
}
}

