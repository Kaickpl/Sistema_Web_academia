package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.GrupoService;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PersonalRepository  personalRepository;

    @PostMapping
    public ResponseEntity<GrupoDTOs> criarGrupo(@RequestBody Grupo grupo) {
        Grupo grupoCriado = grupoService.CriarGrupo(grupo);
        if (grupoCriado == null) {
            return ResponseEntity.notFound().build();
        }
        GrupoDTOs grupoDTOs = new GrupoDTOs(grupoCriado);

        return ResponseEntity.status(201).body(grupoDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removerGrupo(@PathVariable UUID id) {
        Boolean deleted = grupoService.removerGrupo(id);

        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(404).body(false);
    }

    @GetMapping("/BuscarPorNome")
    public ResponseEntity<List<GrupoDTOs>> buscarNome (@RequestParam String nome) {
        List<GrupoDTOs> grupo = grupoService.buscarGrupo(nome).stream().map(grupoCriado -> new GrupoDTOs(grupoCriado)).collect(Collectors.toList());
        return ResponseEntity.ok(grupo);
        }

    @PutMapping("/grupos/{grupoId}/alunos/{idAluno}")
    public ResponseEntity<GrupoDTOs> addAluno(@PathVariable UUID grupoId, @PathVariable UUID idAluno) {

        GrupoDTOs grupoDTO = new GrupoDTOs();
        grupoDTO.setIdGrupo(grupoId);

        Grupo grupoAtualizado = grupoService.AddUsuarioGrupo(idAluno, grupoDTO);
        if (grupoAtualizado == null) {
            return ResponseEntity.notFound().build();
    }
        GrupoDTOs grupoDTOsnovo = new GrupoDTOs(grupoAtualizado);
        return ResponseEntity.ok(grupoDTOsnovo);
    }

    @GetMapping("/ListarTodos")
    public ResponseEntity<Page<GrupoDTOs>> listarTodos(@PageableDefault(size = 5)  Pageable pageable) {
        return ResponseEntity.ok(grupoService.buscarGrupos(pageable).map(GrupoDTOs::new));
    }

    @PutMapping("/DeletarAluno/{grupoId}/{idAluno}")
    public ResponseEntity<GrupoDTOs> RemoverAlunoGrupo(@PathVariable UUID grupoId, @PathVariable UUID idAluno) {
        GrupoDTOs grupoDTO = new GrupoDTOs();
        grupoDTO.setIdGrupo(grupoId);
        Grupo grupoAtualizado = grupoService.removeUsuarioGrupo(idAluno, grupoDTO);
        if (grupoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        GrupoDTOs grupoDTOs = new GrupoDTOs(grupoAtualizado);
        return ResponseEntity.ok(grupoDTOs);
    }


}




