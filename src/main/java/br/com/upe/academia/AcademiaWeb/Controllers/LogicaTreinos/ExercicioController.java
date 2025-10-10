package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelAtualizarExercicio;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarExercicio;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarExercicio;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {
    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private ExercicioMapper exercicioMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioDTO> buscarExercicio(@PathVariable UUID id){
        Exercicio exercicio = exercicioService.buscarExercicio(id);
        ExercicioDTO exercicioDTO = exercicioMapper.toDTO(exercicio);
        return ResponseEntity.ok().body(exercicioDTO);
    }

    @PostMapping
    public ResponseEntity<ExercicioDTO> criarExercicio(@RequestBody ExercicioDTO exercicioDto){
        ExecutavelCriarExercicio comandoCriarExerc = new ExecutavelCriarExercicio(exercicioService, exercicioMapper.toEntity(exercicioDto));
        commandHistory.execute(comandoCriarExerc);
        Exercicio novaSerie = comandoCriarExerc.getExercicioCriado();
        ExercicioDTO exercicioDTO = exercicioMapper.toDTO(novaSerie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novaSerie.getIdExercicio()).toUri();
        return ResponseEntity.created(location).body(exercicioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioDTO> atualizarExercicio(@RequestBody ExercicioDTO exercicioDto, @PathVariable UUID id){
        exercicioDto.setIdExercicio(id);
        Exercicio exercicio = exercicioMapper.toEntity(exercicioDto);
        ExecutavelAtualizarExercicio comandoAttExerc = new ExecutavelAtualizarExercicio(exercicioService, id, exercicio);
        commandHistory.execute(comandoAttExerc);
        Exercicio exercAtualizado = comandoAttExerc.getExercicioAtualizado();
        ExercicioDTO exercicioDTO = exercicioMapper.toDTO(exercAtualizado);
        return  ResponseEntity.ok().body(exercicioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable UUID id){
        ExecutavelDeletarExercicio comandoDelExerc =  new ExecutavelDeletarExercicio(exercicioService, id);
        commandHistory.execute(comandoDelExerc);
        return  ResponseEntity.noContent().build();
    }

}
