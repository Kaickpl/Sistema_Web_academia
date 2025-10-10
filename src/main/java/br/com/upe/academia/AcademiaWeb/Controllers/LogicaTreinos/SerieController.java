package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import java.net.URI;
import java.util.UUID;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.SerieServiceImpl;
import br.com.upe.academia.AcademiaWeb.utils.SerieMapper;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelAtualizarSerie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarSerie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/serie")
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
        SerieDTO serieDTO = serieMapper.toResponseDTO(serie);
        return ResponseEntity.ok().body(serieDTO);
    }

    //A parte do uri serve para dizer que deu certo e mostar o caminho exato para o que criou
    @PostMapping
    public ResponseEntity<SerieDTO> adicionarSerie(@RequestBody SerieDTO seriedto){
        ExecutavelCriarSerie comandoCriarSerie = new ExecutavelCriarSerie(serieService, serieMapper.toEntity(seriedto));
        commandHistory.execute(comandoCriarSerie);
        Serie novaSerie = comandoCriarSerie.getSerieCriada();
        SerieDTO serieDTO = serieMapper.toResponseDTO(novaSerie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novaSerie.getIdSerie()).toUri();
        return ResponseEntity.created(location).body(serieDTO);
}

    @PutMapping("/{id}")
    public ResponseEntity<SerieDTO> atualizarSerie(@RequestBody SerieDTO seriedto, @PathVariable UUID id) {
        seriedto.setIdSerie(id);
        Serie serie = serieMapper.toEntity(seriedto);
        ExecutavelAtualizarSerie comandoAtualizar = new ExecutavelAtualizarSerie(serieService, id, serie);
        commandHistory.execute(comandoAtualizar);
        Serie serieAtualizada = comandoAtualizar.getSerieAtualizada();
        SerieDTO serieAttDTO = serieMapper.toResponseDTO(serieAtualizada);
        return ResponseEntity.ok().body(serieAttDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSerie(@PathVariable UUID id){
        ExecutavelDeletarSerie comandoDeletar = new ExecutavelDeletarSerie(serieService, id);
        commandHistory.execute(comandoDeletar);
        return ResponseEntity.noContent().build();
}
}

