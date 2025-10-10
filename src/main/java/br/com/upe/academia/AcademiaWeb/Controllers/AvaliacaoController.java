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

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody AvaliacaoDTOs avaliacaoDTOs){
        Avaliacao novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoDTOs);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }
}
