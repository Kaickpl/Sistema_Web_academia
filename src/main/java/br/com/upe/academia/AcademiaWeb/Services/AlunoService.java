package br.com.upe.academia.AcademiaWeb.Services;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import org.springframework.data.domain.Page;
import java.awt.print.Pageable;
import java.util.UUID;

public interface AlunoService {
    public Aluno cadastrarAluno(Aluno aluno);
    public Aluno alterarAluno(Aluno aluno);
    public boolean removerAluno(Aluno aluno);
    public boolean findById(UUID id);
    public Page<Aluno> ListarAlunos(Pageable page);
}
