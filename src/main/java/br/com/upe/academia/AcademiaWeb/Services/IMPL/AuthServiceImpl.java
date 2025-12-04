package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.LoginDTOs;
import br.com.upe.academia.AcademiaWeb.Exceptions.CampoObrigatorioException;
import br.com.upe.academia.AcademiaWeb.Exceptions.OperacaoNaoPermitidaException;
import br.com.upe.academia.AcademiaWeb.Exceptions.UsuarioNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import br.com.upe.academia.AcademiaWeb.Services.AuthLogin;
import br.com.upe.academia.AcademiaWeb.Services.PersonalService;
import br.com.upe.academia.AcademiaWeb.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthLogin {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(LoginDTOs loginDTOs) {

        if (loginDTOs.getEmail() == null || loginDTOs.getEmail().isBlank()) {
            throw new CampoObrigatorioException("O campo email não pode estar vazio.");
        }
        if (loginDTOs.getPassword() == null || loginDTOs.getPassword().isBlank()) {
            throw new CampoObrigatorioException("O campo senha não pode estar vazio.");
        }

        // Buscar usuário em uma das tabelas
        var aluno = alunoService.buscarAlunoPorEmail(loginDTOs.getEmail());
        var personal = personalService.buscarPersonalPorEmail(loginDTOs.getEmail());

        if (aluno == null && personal == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        // Pega o usuário real (Aluno ou Personal)
        String senhaBanco = aluno != null
                ? aluno.getPassword()
                : personal.getPassword();

        // Verifica senha
        if (!passwordEncoder.matches(loginDTOs.getPassword(), senhaBanco)) {
            throw new OperacaoNaoPermitidaException("Senha inválida.");
        }

        // Autenticação do Spring
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTOs.getEmail(), loginDTOs.getPassword()
        );

        var auth = authenticationManager.authenticate(authenticationToken);
        UserDetails usuario = (UserDetails) auth.getPrincipal();

        return tokenService.generateToken(usuario);
    }
}
