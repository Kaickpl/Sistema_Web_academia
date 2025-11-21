package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
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
    private UUID idAluno;
    private UUID idTreino;
    private AlunoService alunoService;

    public ExecutavelRemoverTreinoAluno( AlunoService alunoService,UUID idAluno, UUID idTreino) {
        this.alunoService = alunoService;
        this.idAluno = idAluno;
        this.idTreino = idTreino;
    }


    @Override
    public void executar() {
        alunoService.removerTreinoAluno(idAluno, idTreino);
    }

    @Override
    public void desfazer() {
        alunoService.atribuirTreinoAluno(idAluno, idTreino, false);
    }
}

