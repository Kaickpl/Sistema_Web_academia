package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelAtualizarSerie;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarSerie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.UUID;

@RestController("/serie")
public class SerieController {

    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private SerieService serieService;

    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscarSerie(@PathVariable UUID id){
        Serie serie = serieService.buscarSerie(id);
        return ResponseEntity.ok().body(serie);
    }


    //A parte do uri serve para dizer que deu certo e mostar o caminho exato para o que criou
@PostMapping("id")
    public ResponseEntity<Void> adicionarSerie(@RequestBody Serie serie){
        this.commandHistory.execute(new ExecutavelCriarSerie());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(serie.getIdSerie()).toUri();
        return ResponseEntity.created(null).build();
}

@PutMapping("/id")
    public ResponseEntity<Void> atualizarSerie(@RequestBody Serie serie, @PathVariable UUID id){
        serie.setIdSerie(id);
        this.commandHistory.execute(new ExecutavelAtualizarSerie());
        return ResponseEntity.noContent().build();
}

@DeleteMapping
    public ResponseEntity<Void> removerSerie(@PathVariable UUID id){
        this.commandHistory.execute(new ExecutavelCriarSerie());
        return ResponseEntity.noContent().build();
}

}
