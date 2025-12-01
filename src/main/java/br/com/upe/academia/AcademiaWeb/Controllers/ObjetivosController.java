package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivoExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivoRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import br.com.upe.academia.AcademiaWeb.Services.ObjetivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/objetivos")
public class ObjetivosController {
    @Autowired
    private ObjetivosService objetivosService;

    @GetMapping("/{alunoId}")
    public List<ObjetivosResponseDTO> mostrarTodosObjetivos(@PathVariable UUID alunoId) {
        return objetivosService.mostrarTodosObjetivos(alunoId);
    }

    @GetMapping("/{alunoId}/concluidos")
    public List<ObjetivosResponseDTO> mostrarObjetivosConcluidos(@PathVariable UUID alunoId){
        return objetivosService.mostrarObjetivosConcluidos(alunoId);
    }

    @GetMapping("/{alunoId}/nao-concluidos")
    public List<ObjetivosResponseDTO> mostrarObjetivosNaoConcluidos(@PathVariable UUID alunoId){
        return objetivosService.mostrarObjetivosNaoConcluidos(alunoId);
    }


    @PostMapping("/medidas")
    public ResponseEntity<ObjetivosDTO> registrarObjetivoMedidas(@RequestBody ObjetivoRegistroDTO objetivoRegistroDTO){
        Objetivos novoObjetivo = objetivosService.registrarObjetivo(objetivoRegistroDTO);
        ObjetivosDTO dto = new ObjetivosDTO(novoObjetivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/exercicios/{idExercicio}")//recebe o template
    public ResponseEntity<ObjetivosDTO> registrarObjetivoExercicios(@RequestBody ObjetivoExercicioDTO objetivoRegistroDTO, @PathVariable UUID idExercicio){
        Objetivos novoObjetivo = objetivosService.registrarObjetivoExercicio(objetivoRegistroDTO, idExercicio);
        ObjetivosDTO dto = new ObjetivosDTO(novoObjetivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
//precisa msm disso?
    @PutMapping("/{idObjetivo}")
    public ResponseEntity<ObjetivosDTO> atualizarObjetivo(@RequestBody ObjetivoRegistroDTO objetivoRegistroDTO, @PathVariable UUID idObjetivo){
        Objetivos objetivoAtualizado = objetivosService.atualizaObjetivo(idObjetivo, objetivoRegistroDTO);
        ObjetivosDTO dto = new ObjetivosDTO(objetivoAtualizado);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
