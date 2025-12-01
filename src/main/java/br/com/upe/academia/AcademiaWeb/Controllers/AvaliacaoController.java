package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/aluno/{alunoId}")
    public List<AvaliacaoResponseDTO> listarAvaliacaoAluno(@PathVariable UUID alunoId) {
        return avaliacaoService.mostrarAvaliacaoAluno(alunoId);
    }

    @GetMapping("/aluno/{alunoId}/proxima")
    public AvaliacaoResponseDTO mostrarProximaAvaliacaoAluno(@PathVariable UUID alunoId){
        return avaliacaoService.mostrarProximaAvaliacaoAluno(alunoId);
    }

    @GetMapping("/personal/{cref}")
    public List<AvaliacaoResponseDTO> listarAvaliacaoPersonal(@PathVariable String cref) {
        return avaliacaoService.mostrarAvaliacaoPersonal(cref);
    }

    @GetMapping("/personal/{cref}/{dataAvaliacao}")
    public List<AvaliacaoResponseDTO> listarAvaliacaoData(@PathVariable String cref, @PathVariable LocalDate dataAvaliacao) {
        return avaliacaoService.mostrarAvaliacaoPersonalEData(cref, dataAvaliacao);
    }

    //delete
    @DeleteMapping("/{idAvaliacao}")
    public ResponseEntity<Boolean> deletarAluno(@PathVariable UUID idAvaliacao) {
        boolean foiDeletado = avaliacaoService.removerAvaliacao(idAvaliacao);
        if(foiDeletado) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(404).body(false);
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criarAvaliacao(@RequestBody AvaliacaoDTOs avaliacaoDTOs){
        Avaliacao novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoDTOs);
        AvaliacaoResponseDTO responseDTO = new AvaliacaoResponseDTO(novaAvaliacao);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //atualiza data
    @PutMapping("/{idAvaliacao}/atualizar/data")
    public ResponseEntity<AvaliacaoDTOs> atualizarData(@RequestBody ModificarDataAvaliacaoDTO avaliacaoDTOs, @PathVariable UUID idAvaliacao){
        Avaliacao avaliacaoAtualizada = avaliacaoService.alterarDataAvaliacao(idAvaliacao, avaliacaoDTOs);
        AvaliacaoDTOs dto = new AvaliacaoDTOs(avaliacaoAtualizada);
        return ResponseEntity.ok(dto);
    }
    //atualiza personal
    @PutMapping("/{idAvaliacao}/atualizar/personal")
    public ResponseEntity<AvaliacaoDTOs> atualizarPersonal(@RequestBody ModificarPersonalAvaliacaoDTO avaliacaoDTOs, @PathVariable UUID idAvaliacao){
        Avaliacao avaliacaoAtualizada = avaliacaoService.alterarPersonal(idAvaliacao, avaliacaoDTOs);
        AvaliacaoDTOs dto = new AvaliacaoDTOs(avaliacaoAtualizada);
        return ResponseEntity.ok(dto);
    }
}
