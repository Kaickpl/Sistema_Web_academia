package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExecutavelRemoverTreinoAluno implements Executavel {
    UUID idAluno;
    UUID idTreino;
    AlunoService alunoService;

    @Override
    public void executar() {
        alunoService.removerTreinoAluno(idAluno, idTreino);
    }

    @Override
    public void desfazer() {
        alunoService.atribuirTreinoAluno(idAluno, idTreino);
    }
}

