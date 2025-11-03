package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface AlunoService {
    public Aluno cadastrarAluno(AlunoDTOs alunoDTOs);

    public boolean existeEmail(String email);

    public Aluno alterarAluno(UUID id, AlunoDTOs alunoDTOs);

    public void removerAluno(UUID ID);

    public List<Aluno> buscaraluno(String nomeUsuario);

    public Page<Aluno> ListarAlunos(Pageable page);

    public Aluno TrocarSenha(String Email, TrocaSenhaDTOs senhaDTOs);

    public Boolean ValidarEmail(String email);

}

