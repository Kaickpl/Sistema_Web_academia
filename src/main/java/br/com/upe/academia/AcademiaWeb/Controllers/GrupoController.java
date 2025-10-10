package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.GrupoService;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}




