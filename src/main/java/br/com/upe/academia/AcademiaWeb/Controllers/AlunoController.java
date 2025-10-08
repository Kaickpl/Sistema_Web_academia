package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
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
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoDTOs alunoDTOs) {

        Aluno aluno = convertToEntity(alunoDTOs);
        if (alunoService.validaremail(aluno.getEmail())) {
            return ResponseEntity.status(409).body("Erro: aluno já cadastrado com esse email!");
        }
        Aluno alunoCadastrado = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(201).body(alunoCadastrado);
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
    public ResponseEntity<List<AlunoDTOs>> buscaraluno(@RequestParam String nome) {
        List<AlunoDTOs> alunos = alunoService.buscaraluno(nome).stream().map(AlunoDTOs::new).collect(Collectors.toList());
        if (alunos != null) {
            return ResponseEntity.status(200).body(alunos);

        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<Page<AlunoDTOs>> listar(@PageableDefault(size = 2)Pageable page) {
        return ResponseEntity.ok(alunoService.ListarAlunos(page).map(AlunoDTOs::new));
    }



    private Aluno convertToEntity(AlunoDTOs alunoDTOs) {
        Aluno aluno = new Aluno();
        aluno.setIdUsuario(alunoDTOs.getIdUsuario());
        aluno.setNomeUsuario(alunoDTOs.getNomeUsuario());
        aluno.setEmail(alunoDTOs.getEmail());
        aluno.setSenha(alunoDTOs.getSenha());
        aluno.setTipo(Tipo.aluno);
        aluno.setSaldoMoedas(alunoDTOs.getSaldoMoedas());
        return aluno;
    }



}
