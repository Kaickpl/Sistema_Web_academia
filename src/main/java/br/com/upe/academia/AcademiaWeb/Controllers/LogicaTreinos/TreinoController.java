package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.*;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioMapper;
import br.com.upe.academia.AcademiaWeb.utils.TreinoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/treino")
public class TreinoController {
    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private TreinoMapper treinoMapper;
    @Autowired
    private ExercicioService exercicioService;
    @Autowired
    private ExercicioMapper exercicioMapper;

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDTO> buscarTreino(@PathVariable("id") UUID id){
        Treino treino = treinoService.buscarTreino(id);
        TreinoDTO treinoDTO = treinoMapper.toDTO(treino);
        return ResponseEntity.ok().body(treinoDTO);
    }

    @PostMapping
    public ResponseEntity<TreinoDTO> criarTreino(@RequestBody TreinoDTO treinoDto){
        ExecutavelCriarTreino comandoCriarTreino = new ExecutavelCriarTreino(treinoService, treinoMapper.toEntity(treinoDto));
        commandHistory.execute(comandoCriarTreino);
        Treino novoTreino =  comandoCriarTreino.getTreinoCriado();
        TreinoDTO treinoDTO = treinoMapper.toDTO(novoTreino);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novoTreino.getIdTreino()).toUri();
        return ResponseEntity.created(location).body(treinoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(@RequestBody TreinoDTO treinoDto, @PathVariable("id") UUID idTreino){
        treinoDto.setIdTreino(idTreino);
        Treino treino = treinoMapper.toEntity(treinoDto);
        ExecutavelAtualizarTreino comandoAttTreino = new ExecutavelAtualizarTreino(treinoService, idTreino, treino);
        commandHistory.execute(comandoAttTreino);
        Treino treinoAtt =  comandoAttTreino.getTreinoAtualizado();
        TreinoDTO treinoDTO = treinoMapper.toDTO(treinoAtt);
        return ResponseEntity.ok().body(treinoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TreinoDTO> deletarTreino(@PathVariable("id") UUID idTreino){
        ExecutavelDeletarTreino comandoDelTreino = new ExecutavelDeletarTreino(treinoService ,idTreino);
        commandHistory.execute(comandoDelTreino);
        return ResponseEntity.noContent().build();
    }

}
