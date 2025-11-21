package br.com.upe.academia.AcademiaWeb.Controllers.Auth;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.*;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
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

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginDTOs loginDTOs) {
        System.out.println("chegou aqui");
        System.out.println(loginDTOs.getEmail() + loginDTOs.getPassword());
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDTOs.getEmail(),
                loginDTOs.getPassword()
        );
        System.out.println("chegou aqui 2");
        var auth = this.authenticationManager.authenticate(usernamePassword);

        System.out.println("chegou aqui 3");
        // Pode ser Personal OU Aluno
        UserDetails usuario = (UserDetails) auth.getPrincipal();

        String token = tokenService.generateToken(usuario);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/cadastro/Personal")
    public ResponseEntity<PersonalResponseDTOs> CadastroPersonal(@RequestBody PersonalDTOs personalDTOs){
        Personal personal = personalService.cadastrarPersonal(personalDTOs);
        if (personal == null){
            return ResponseEntity.badRequest().build();
        }
        PersonalResponseDTOs dto = new PersonalResponseDTOs(personal);
        return ResponseEntity.ok(dto);

    }
    @PostMapping("cadastro/aluno")
    public ResponseEntity<AlunoResponseDTOs> cadastrarAluno(@RequestBody AlunoDTOs alunoDTOs) {
        Aluno aluno = alunoService.cadastrarAluno(alunoDTOs);
        if (aluno == null) {
            return ResponseEntity.badRequest().build();
        }
        AlunoResponseDTOs dto = new AlunoResponseDTOs(aluno);
        return ResponseEntity.ok(dto);
    }


}
