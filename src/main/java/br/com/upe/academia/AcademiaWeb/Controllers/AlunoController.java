package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTOs> cadastrarAluno(@RequestBody AlunoDTOs alunoDTOs) {
        Aluno aluno = alunoService.cadastrarAluno(alunoDTOs);
        if (aluno == null) {
            return ResponseEntity.badRequest().build();
        }
        AlunoResponseDTOs dto = new AlunoResponseDTOs(aluno);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAluno(@PathVariable UUID id) {
        boolean deletado = alunoService.removerAluno(id);
        if (deletado) {
           return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(404).body(false);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlunoResponseDTOs>> buscaraluno(@RequestParam String nome) {
        List<AlunoResponseDTOs> alunos = alunoService.buscaraluno(nome).stream().map(AlunoResponseDTOs::new).collect(Collectors.toList());
        if (alunos != null) {
            return ResponseEntity.status(200).body(alunos);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<Page<AlunoResponseDTOs>> listar(@PageableDefault(size = 2)Pageable page) {
        return ResponseEntity.ok(alunoService.ListarAlunos(page).map(AlunoResponseDTOs::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTOs> atualizarAluno(@RequestBody AlunoDTOs alunoDTOs, @PathVariable UUID id) {

        Aluno alunoExistente = alunoService.alterarAluno(id,alunoDTOs);
        if (alunoExistente == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new AlunoResponseDTOs(alunoExistente));
    }











}
