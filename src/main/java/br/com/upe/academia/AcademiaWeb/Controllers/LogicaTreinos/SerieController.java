package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import java.net.URI;
import java.util.UUID;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
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

    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscarSerie(@PathVariable UUID id){
        Serie serie = serieService.buscarSerie(id);
        return ResponseEntity.ok().body(serie);
    }

    //A parte do uri serve para dizer que deu certo e mostar o caminho exato para o que criou
    @PostMapping
    public ResponseEntity<Serie> adicionarSerie(@RequestBody Serie serie){
        ExecutavelCriarSerie comandoCriarSerie = new ExecutavelCriarSerie(serieService, serie);
        commandHistory.execute(comandoCriarSerie);
        Serie novaSerie = comandoCriarSerie.getSerieCriada();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novaSerie.getIdSerie()).toUri();
        return ResponseEntity.created(location).body(novaSerie);
}

    @PutMapping("/{id}")
    public ResponseEntity<Serie> atualizarSerie(@RequestBody Serie serie, @PathVariable UUID id) {
        serie.setIdSerie(id);
        ExecutavelAtualizarSerie comandoAtualizar = new ExecutavelAtualizarSerie(serieService, serie.getIdSerie(), serie);
        commandHistory.execute(comandoAtualizar);
        Serie serieAtualizada = comandoAtualizar.getSerieAtualizada();
        return ResponseEntity.ok().body(serieAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSerie(@PathVariable UUID id){
        ExecutavelDeletarSerie comandoDeletar = new ExecutavelDeletarSerie(serieService, id);
        commandHistory.execute(comandoDeletar);
        return ResponseEntity.noContent().build();
}
}

