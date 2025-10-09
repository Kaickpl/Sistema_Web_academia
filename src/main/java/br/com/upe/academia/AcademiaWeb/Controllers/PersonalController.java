package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/personal")
@RestController
public class PersonalController {

    @Autowired
    private PersonalService  personalService;


    @PostMapping
    public ResponseEntity<?>CadastroPersonal(@RequestBody PersonalDTOs personalDTOs){
        Personal personal = convertToEntity(personalDTOs);
        if (personalService.validarEmail(personal.getEmail())){
            return ResponseEntity.status(409).body("Usuário já cadastrado com essa email!");}
        if (personal.getCref() == null || (personal.getCref().isEmpty())){
            return ResponseEntity.status(409).body("Preencha o campo cref!");
        }
        if(personal.getSenha() == null || (personal.getSenha().isEmpty())){
            return ResponseEntity.status(409).body("Insira um SENHA valida!");
        }
        if(personalService.validarCref(personal.getCref())){
            return ResponseEntity.status(409).body("Já existe um CREF cadastrado, insira um CREF valido!");
        }
        personal = personalService.cadastrarPersonal(personal);
        return ResponseEntity.status(200).body(personal);

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
    public ResponseEntity<List<PersonalDTOs >> buscarPersonal(@RequestParam String nome) {
        List<PersonalDTOs> personal = personalService.buscarPersonalNome(nome).stream().map(PersonalDTOs::new).toList();
        if (personal != null) {
            return ResponseEntity.status(200).body(personal);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/buscarCref")
    public ResponseEntity<?> buscarPersonalCref(@RequestParam String cref) {
        Personal personal = personalService.buscarPersonal(cref);
        if (personal != null) {
            PersonalDTOs personalDTOs = convertToDTO(personal);
            return ResponseEntity.status(200).body(personalDTOs);
        }
        return ResponseEntity.status(404).body("Personal não encontrado");
    }

    @PutMapping("/{cref}")
    public ResponseEntity<PersonalDTOs> atualizarPersonal(@PathVariable String cref, @RequestBody PersonalDTOs personalDTOs) {
        Personal personal = convertToEntity(personalDTOs);
        Personal personalAtualizado = personalService.alterarPersonal(cref, personal);
        PersonalDTOs personalAtualizadoDTO = convertToDTO(personalAtualizado);
        return ResponseEntity.status(200).body(personalAtualizadoDTO);
    }




    //recebe e convert
    private Personal convertToEntity(PersonalDTOs personalDTOs) {
        Personal personal = new Personal();
        personal.setIdUsuario(personalDTOs.getIdUsuario());
        personal.setDataNascimento(personalDTOs.getDataNascimento());
        personal.setNomeUsuario(personalDTOs.getNomeUsuario());
        personal.setEmail(personalDTOs.getEmail());
        personal.setSenha(personalDTOs.getSenha());
        personal.setTelefone(personalDTOs.getTelefone());
        personal.setTipo(Tipo.aluno);
        personal.setCref(personalDTOs.getCref());
        return personal;
    }
//recebe e convert
    private PersonalDTOs convertToDTO(Personal personal) {
        PersonalDTOs dto = new PersonalDTOs();
        //dto.setIdUsuario(personal.getIdUsuario());
        //dto.setDataNascimento(personal.getDataNascimento());
        dto.setNomeUsuario(personal.getNomeUsuario());
        dto.setEmail(personal.getEmail());
        //dto.setSenha(personal.getSenha());
        dto.setTelefone(personal.getTelefone());
        //dto.setTipo(personal.getTipo());
        dto.setCref(personal.getCref());
        return dto;
    }

}
