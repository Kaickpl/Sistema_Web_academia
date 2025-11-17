package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AvaliacaoDTOs;
import br.com.upe.academia.AcademiaWeb.Services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/aluno/{alunoId}")
    public List<Avaliacao> listarAvaliacaoAluno(@PathVariable UUID alunoId) {
        return avaliacaoService.mostrarAvaliacaoAluno(alunoId);
    }

    @GetMapping("/personal/{cref}")
    public List<Avaliacao> listarAvaliacaoPersonal(@PathVariable String cref) {
        return avaliacaoService.mostrarAvaliacaoPersonal(cref);
    }

    @GetMapping("/personal/{cref}/{dataAvaliacao}")
    public List<Avaliacao> listarAvaliacaoData(@PathVariable String cref, @PathVariable LocalDate dataAvaliacao) {
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
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody AvaliacaoDTOs avaliacaoDTOs){
        Avaliacao novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoDTOs);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    //atualiza data
    @PutMapping("/{idAvaliacao}")
    public ResponseEntity<?> atualizarData(@RequestBody AvaliacaoDTOs avaliacaoDTOs, @PathVariable UUID idAvaliacao){
        Avaliacao avaliacaoExiste = avaliacaoService.buscarPorId(idAvaliacao);
        //tirar issoe por no service
        if (avaliacaoExiste == null) {
            return ResponseEntity.status(404).body("Avaliação não encontrada");
        }

        if (avaliacaoDTOs.getDataAvaliacao() != null){
            avaliacaoExiste.setDataAvaliacao(avaliacaoDTOs.getDataAvaliacao());
        }

        Avaliacao avaliacaoAtualizada = avaliacaoService.alterarDataAvaliacao(idAvaliacao, avaliacaoExiste);

        return ResponseEntity.ok(avaliacaoAtualizada);
    }
}
