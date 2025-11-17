package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoEmGrupoDTOsResponse;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoResponseGrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.GrupoService;
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
@RequestMapping("/api/Grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PersonalRepository  personalRepository;

    @Autowired
    private AlunoService alunoService;

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
    public ResponseEntity<Void> removerGrupo(@PathVariable UUID id) {
        grupoService.removerGrupo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/BuscarPorNome")
    public ResponseEntity<List<GrupoDTOs>> buscarNome (@RequestParam String nome) {
        List<GrupoDTOs> grupo = grupoService.buscarGrupo(nome).stream().map(grupoCriado -> new GrupoDTOs(grupoCriado)).collect(Collectors.toList());
        return ResponseEntity.ok(grupo);
        }

    @GetMapping("/ListarTodos")
    public ResponseEntity<Page<GrupoDTOs>> listarTodos(@PageableDefault(size = 5)  Pageable pageable) {
        return ResponseEntity.ok(grupoService.buscarGrupos(pageable).map(GrupoDTOs::new));
    }

    @PutMapping("/AddAluno/{grupoId}")
    public ResponseEntity<GrupoDTOs> AddAluno(@PathVariable UUID grupoId, @RequestBody UUID id) {
        GrupoDTOs grupoDTOs = new GrupoDTOs();
        grupoDTOs.setIdGrupo(grupoId);
        Grupo grupoAtualizado = grupoService.AddUsuarioGrupo(id,grupoDTOs);
        if (grupoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        GrupoDTOs grupoDTOsAtualizado = new GrupoDTOs(grupoAtualizado);
        return ResponseEntity.status(201).body(grupoDTOsAtualizado);
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

    @PutMapping("/EditarGrupo/{id}")
    public ResponseEntity<GrupoDTOs> editarGrupo(@PathVariable UUID id, @RequestBody GrupoDTOs grupoDTOs) {
        Grupo grupo = grupoService.editarGrupo(id, grupoDTOs);
        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupoDTOs);
    }
    @GetMapping("/ListarAlunosGrupo")
    public ResponseEntity<AlunoEmGrupoDTOsResponse> listarAlunosGrupo(@RequestParam String nomeGrupo) {
        Grupo grupo = grupoService.BuscarAlunoGrupo(nomeGrupo);

        AlunoEmGrupoDTOsResponse aln = new AlunoEmGrupoDTOsResponse(grupo);

        return ResponseEntity.ok(aln);
    }

}




