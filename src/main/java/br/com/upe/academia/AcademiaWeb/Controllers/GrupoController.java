package br.com.upe.academia.AcademiaWeb.Controllers;

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
        if (grupo.getPersonal() == null || grupo.getPersonal().getIdUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }

        Personal personal = personalRepository.findById(grupo.getPersonal().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Personal não encontrado"));

        grupo.setPersonal(personal);
        grupo.setAlunos(new ArrayList<>()); // inicializa lista vazia

        Grupo grupoCriado = grupoService.CriarGrupo(grupo);

        // converter para DTO
        GrupoDTOs grupoDTO = new GrupoDTOs(grupoCriado);

        return ResponseEntity.status(201).body(grupoDTO);
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
}




