package br.com.upe.academia.AcademiaWeb.Controllers.Auth;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.AuthLogin;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import br.com.upe.academia.AcademiaWeb.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacao")
public class AuthUsuario {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AuthLogin  authLogin;

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginDTOs loginDTOs) {

        String token = authLogin.login(loginDTOs);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/cadastro/Personal")
    public ResponseEntity<PersonalResponseDTOs> CadastroPersonal(@RequestBody PersonalDTOs personalDTOs){
        Personal personal = personalService.cadastrarPersonal(personalDTOs);
        PersonalResponseDTOs dto = new PersonalResponseDTOs(personal);
        return ResponseEntity.ok(dto);

    }
    @PostMapping("cadastro/aluno")
    public ResponseEntity<AlunoResponseDTOs> cadastrarAluno(@RequestBody AlunoDTOs alunoDTOs) {
        Aluno aluno = alunoService.cadastrarAluno(alunoDTOs);
        AlunoResponseDTOs dto = new AlunoResponseDTOs(aluno);
        return ResponseEntity.ok(dto);
    }


}
