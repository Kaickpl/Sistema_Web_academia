package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/personal")
@RestController
public class PersonalController {

    @Autowired
    private PersonalService  personalService;


    @PostMapping
    public ResponseEntity<PersonalResponseDTOs> CadastroPersonal(@RequestBody PersonalDTOs personalDTOs){
        Personal personal = personalService.cadastrarPersonal(personalDTOs);
        if (personal == null){
            return ResponseEntity.badRequest().build();
        }
        PersonalResponseDTOs dto = new PersonalResponseDTOs(personal);
        return ResponseEntity.ok(dto);

    }
    @DeleteMapping("/{cref}")
    public ResponseEntity<Boolean> deletarPersonal (@PathVariable String cref){
        Boolean deletado = personalService.deletarPersonal(cref);
        if (deletado) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(404).body(false);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PersonalResponseDTOs>> buscarPersonal(@RequestParam String nome) {
        List<Personal> personal = personalService.buscarPersonalNome(nome);
        if (personal.isEmpty()) {
            return  ResponseEntity.status(404).body(null);
        }
        List<PersonalResponseDTOs> dto = personal.stream().map(PersonalResponseDTOs::new).collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscarCref")
    public ResponseEntity<PersonalResponseDTOs> buscarPersonalCref(@RequestParam String cref) {
        Personal personal = personalService.buscarPersonal(cref);
        if (personal == null) {
            return ResponseEntity.badRequest().build();
        }
        PersonalResponseDTOs dto = new PersonalResponseDTOs(personal);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{cref}")
    public ResponseEntity<PersonalResponseDTOs> atualizarPersonal(@PathVariable String cref, @RequestBody PersonalDTOs personalDTOs) {
        Personal personalExiste = personalService.alterarPersonal(cref, personalDTOs);
        if (personalExiste == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new PersonalResponseDTOs(personalExiste));
    }
    @PutMapping("/RecuperarSenha/{id}")
    public ResponseEntity<PersonalResponseDTOs> atualizarSenha(@PathVariable UUID id, @RequestBody TrocaSenhaDTOs senhaDTOs) {
      Personal personal = personalService.TrocaSenha(id, senhaDTOs);
      if (personal == null) {
          return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.ok(new PersonalResponseDTOs(personal));
    }
}
