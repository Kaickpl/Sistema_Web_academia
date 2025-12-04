package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TrocaSenhaDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface AlunoService {
    public Aluno cadastrarAluno(AlunoDTOs alunoDTOs);

    public boolean existeEmail(String email);

    public Aluno alterarAluno(UUID id, AlunoDTOs alunoDTOs);

    public void removerAluno(UUID ID);

    public List<Aluno> buscarAlunoPorNome(String nomeUsuario);

    public Aluno buscarAlunoPorId(UUID idAluno);

    public Page<Aluno> listarAlunos(Pageable page);

    public Aluno trocarSenha(TrocaSenhaDTOs senhaDTOs);

    public Boolean validarEmail(String email);

    public List<Treino> listarTreinos(UUID idAluno);

    public Treino atribuirTreinoAluno(UUID idAluno, UUID idTreino, boolean isCopiaCompartilhada);

    public void removerTreinoAluno(UUID idAluno, UUID idTreino);

    public Treino buscarTreinoUnico(UUID idAluno,UUID idTreino);

    public List<Grupo> ListarGruposAluno(UUID idAluno);

    public Aluno buscarAlunoPorEmail(String email);

    public List<UUID> buscarIdAlunoPorTreino(UUID idTreino);

    public AlunoResponseDTOs VerPerfil(UUID idAluno);
}

