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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            return ResponseEntity.status(409).body("Insira um CREF valido!");
        }
        if(personal.getSenha() == null || (personal.getSenha().isEmpty())){
            return ResponseEntity.status(409).body("Insira um SENHA valida!");
        }
        personal = personalService.cadastrarPersonal(personal);
        return ResponseEntity.status(200).body(personal);

    }
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

}
