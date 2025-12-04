package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelAddComentarioExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelCriarExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis.ExecutavelDeletarExercicioSessao;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioSessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sessoes/{idSessao}/exercicios")
public class ExercicioSessaoController {

    @Autowired
    private ExercicioSessaoService exercicioSessaoService;

    @Autowired
    private CommandHistory commandHistory;

    @Autowired
    private ExercicioSessaoMapper exercicioSessaoMapper;

    @PostMapping
    public ResponseEntity<ExercicioSessaoDTO> registrarSessaoExercicio(@PathVariable UUID idSessao ,@RequestBody @Validated ExercicioSessaoDTO exercicioSessaoDTO){
        exercicioSessaoDTO.setIdTreinoSessao(idSessao);
        ExecutavelCriarExercicioSessao comandoCriarExercicioSessao = new ExecutavelCriarExercicioSessao(this.exercicioSessaoService, exercicioSessaoDTO);
        commandHistory.execute(comandoCriarExercicioSessao);
        exercicioSessaoDTO.setIdExercicioSessao(comandoCriarExercicioSessao.getIdExercicioSessao());
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioSessaoDTO);
    }

    @DeleteMapping("/{idExercicioSessao}")
    public ResponseEntity<Void> deletarExercicioSessao(@PathVariable UUID idSessao ,@PathVariable UUID idExercicioSessao){
        ExercicioSessaoDTO exercicioSessaoDTO = exercicioSessaoMapper.toDTO(exercicioSessaoService.buscarExercicioSessao(idSessao, idExercicioSessao));
        ExecutavelDeletarExercicioSessao comandoDeletarExercicioSessao = new ExecutavelDeletarExercicioSessao(this.exercicioSessaoService, this.exercicioSessaoMapper, exercicioSessaoDTO);
        commandHistory.execute(comandoDeletarExercicioSessao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idExercicioSessao}")
    public ResponseEntity<ExercicioSessaoResponseDTO> buscarExercicioSessao(@PathVariable UUID idSessao ,@PathVariable UUID idExercicioSessao){
        ExercicioSessao exercicioReff = exercicioSessaoService.buscarExercicioSessao(idSessao, idExercicioSessao);
        ExercicioSessaoResponseDTO exercicioSessaoResponseDTO = exercicioSessaoMapper.toReponseDTO(exercicioReff);
        return ResponseEntity.ok(exercicioSessaoResponseDTO);
    }

    @PutMapping("/{idExercicioSessao}")
    public ResponseEntity<ExercicioSessaoResponseDTO> adicionarComentario(@PathVariable UUID idSessao ,@PathVariable UUID idExercicioSessao, @RequestBody ComentarioDTO comentarioDTO){
        ExecutavelAddComentarioExercicioSessao comandoAddComentario = new ExecutavelAddComentarioExercicioSessao(this.exercicioSessaoService, idSessao , idExercicioSessao, comentarioDTO);
        commandHistory.execute(comandoAddComentario);
        ExercicioSessao exercicioSessao = exercicioSessaoService.buscarExercicioSessao(idSessao, idExercicioSessao);
        ExercicioSessaoResponseDTO dto = exercicioSessaoMapper.toReponseDTO(exercicioSessao);
        return ResponseEntity.ok(dto);
    }
}
