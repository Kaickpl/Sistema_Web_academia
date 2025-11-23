package br.com.upe.academia.AcademiaWeb.Controllers.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.CommandHistory;
import br.com.upe.academia.AcademiaWeb.Services.TreinoExercicioService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoExercicioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/treino-exercicios")
public class TreinoExercicioController {
    @Autowired
    private TreinoExercicioService treinoExercicioService;

    @Autowired
    private CommandHistory commandHistory;


    @Autowired
    private TreinoExercicioMapper treinoExercicioMapper;

    @Autowired
    private ExercicioService exercicioService;

    @PostMapping("/treinos/{idTreino}/exercicios/{idExercicio}")
    public ResponseEntity<TreinoExercicioResponseDTO> adicionarExercicioAoTreino(@PathVariable UUID idTreino, @PathVariable UUID idExercicio) {
        TreinoExercicioDTO treinoExercicioDTO = new TreinoExercicioDTO();
        treinoExercicioDTO.setIdExercicio(idExercicio);
        treinoExercicioDTO.setIdTreino(idTreino);
        treinoExercicioDTO.setTempoDeDescanso("00:00:00");

        TreinoExercicio regraCriada = treinoExercicioService.salvarRegra(treinoExercicioMapper.toEntity(treinoExercicioDTO));

        Exercicio exercicioInfo = exercicioService.buscarExercicio(idExercicio);
        regraCriada.setExercicioTemplate(exercicioInfo);

        TreinoExercicioResponseDTO treinoExercicioResponseDTO = treinoExercicioMapper.toResponseDTO(regraCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoExercicioResponseDTO);
    }

    @PutMapping("/{idTreinoExercicio}")
    public ResponseEntity<TreinoExercicioResponseDTO> atualizarTempoDeDescanso(@PathVariable UUID idTreinoExercicio, @RequestBody TempoDeDescansoDTO tempoDeDescansoDTO) {
        TreinoExercicio regraAtualizada = treinoExercicioService.atualizarTempoDeDescanso(idTreinoExercicio, tempoDeDescansoDTO);
        TreinoExercicioResponseDTO treinoExercicioResponseDTO = treinoExercicioMapper.toResponseDTO(regraAtualizada);
        return ResponseEntity.status(HttpStatus.OK).body(treinoExercicioResponseDTO);
    }

}
