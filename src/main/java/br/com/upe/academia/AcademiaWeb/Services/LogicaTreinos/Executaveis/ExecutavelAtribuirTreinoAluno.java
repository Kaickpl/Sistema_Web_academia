package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutavelAtribuirTreinoAluno implements Executavel {
    private AlunoService alunoService;
    private UUID idAluno;
    private UUID idTreino;

    @Override
    public void executar() {
        alunoService.atribuirTreinoAluno(idAluno, idTreino);
    }

    @Override
    public void desfazer() {
        alunoService.removerTreinoAluno(idAluno, idTreino);
    }
}
