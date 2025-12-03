package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
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


    @DeleteMapping("/{cref}")
    public ResponseEntity<Void> deletarPersonal (@PathVariable String cref){
      personalService.deletarPersonal(cref);
      return ResponseEntity.ok().build();
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
    @PutMapping("/RecuperarSenha")
    public ResponseEntity<PersonalResponseDTOs> atualizarSenha( @RequestBody TrocaSenhaDTOs senhaDTOs) {
      Personal personal = personalService.TrocaSenha( senhaDTOs);
      if (personal == null) {
          return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.ok(new PersonalResponseDTOs(personal));
    }
    @GetMapping("/ListarGruposPersonal/{idPersonal}")

    public ResponseEntity<List<GrupoDTOs>> ListarGruposPersonal(@PathVariable UUID idPersonal) {
            List<Grupo> grupo = personalService.ListaGruposPersonal(idPersonal);
            if (grupo.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            List<GrupoDTOs> gda = grupo.stream().map(GrupoDTOs::new)
                    .toList();
            return ResponseEntity.ok(gda);
    }
}
