package br.com.upe.academia.AcademiaWeb.Controllers;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
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
        if(aluno.getSenha() == null || (aluno.getSenha().isEmpty())){
            return ResponseEntity.status(409).body("Insira um SENHA valida!");
        }
        if(aluno.getNomeUsuario()  == null || (aluno.getNomeUsuario().isEmpty())){
            return ResponseEntity.status(409).body("Insira um NOME valido!");
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@RequestBody AlunoDTOs alunoDTOs, @PathVariable UUID id) {

        Aluno alunoExistente = alunoService.buscarPorId(id);
        if (alunoExistente == null) {
            return ResponseEntity.status(404).body("Aluno não encontrado");
        }
        if (alunoDTOs.getNomeUsuario() != null) {
            alunoExistente.setNomeUsuario(alunoDTOs.getNomeUsuario());
        }
        if (alunoDTOs.getTelefone() != null) {
            alunoExistente.setTelefone(alunoDTOs.getTelefone());
        }
        if (alunoDTOs.getEmail() != null && !alunoDTOs.getEmail().equals(alunoExistente.getEmail())) {
            if (alunoService.validaremail(alunoDTOs.getEmail())) {
                return ResponseEntity.status(409).body("Já existe um aluno com esse email!");
            }
            alunoExistente.setEmail(alunoDTOs.getEmail());
        }
        Aluno alunoAtualizado = alunoService.alterarAluno(id, alunoExistente);

        return ResponseEntity.ok(alunoAtualizado);
    }







    private Aluno convertToEntity(AlunoDTOs alunoDTOs) {
        Aluno aluno = new Aluno();
        //aluno.setIdUsuario(alunoDTOs.getIdUsuario());
        aluno.setDataNascimento(alunoDTOs.getDataNascimento());
        aluno.setNomeUsuario(alunoDTOs.getNomeUsuario());
        aluno.setEmail(alunoDTOs.getEmail());
        //aluno.setSenha(alunoDTOs.getSenha());
        aluno.setTelefone(alunoDTOs.getTelefone());
        //aluno.setTipo(Tipo.aluno);
        aluno.setSaldoMoedas(alunoDTOs.getSaldoMoedas());
        return aluno;
    }



}
