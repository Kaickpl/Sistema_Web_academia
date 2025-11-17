package br.com.upe.academia.AcademiaWeb.Controllers.Auth;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.LoginDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.PersonalResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import br.com.upe.academia.AcademiaWeb.Services.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/autenticacao")
public class AuthPersonal {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginDTOs loginDTOs) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTOs.getEmail(), loginDTOs.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Personal personal = (Personal) auth.getPrincipal();
        String token = tokenService.generateToken(personal);
        return  ResponseEntity.ok().body(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<PersonalResponseDTOs> CadastroPersonal(@RequestBody PersonalDTOs personalDTOs){
        Personal personal = personalService.cadastrarPersonal(personalDTOs);
        if (personal == null){
            return ResponseEntity.badRequest().build();
        }
        PersonalResponseDTOs dto = new PersonalResponseDTOs(personal);
        return ResponseEntity.ok(dto);

    }


}
