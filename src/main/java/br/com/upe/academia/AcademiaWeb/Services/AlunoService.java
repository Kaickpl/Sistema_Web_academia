package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface AlunoService {
    public Aluno cadastrarAluno(Aluno aluno);

    public boolean validaremail(String email);

    public Aluno alterarAluno(UUID id, Aluno aluno);

    public boolean removerAluno(UUID ID);

    public List<Aluno> buscaraluno(String nomeUsuario);

    public Aluno buscarPorId(UUID id);


    public Page<Aluno> ListarAlunos(Pageable page);
}

