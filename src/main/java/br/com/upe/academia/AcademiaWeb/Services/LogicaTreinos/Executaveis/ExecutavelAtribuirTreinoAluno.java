package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AlunoTreinoDTO;
import br.com.upe.academia.AcademiaWeb.Services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ExecutavelAtribuirTreinoAluno implements Executavel {
    private AlunoService alunoService;
    private UUID idAluno;
    private UUID idTreino;
    private boolean isCopiaCompartilhada;

    public ExecutavelAtribuirTreinoAluno(AlunoService alunoService, UUID idAluno, UUID idTreino, boolean isCopiaCompartilhada){
        this.alunoService = alunoService;
        this.idAluno = idAluno;
        this.idTreino = idTreino;
        this.isCopiaCompartilhada = isCopiaCompartilhada;
    }

    @Override
    public void executar() {
        alunoService.atribuirTreinoAluno(idAluno, idTreino, isCopiaCompartilhada);
    }

    @Override
    public void desfazer() {
        alunoService.removerTreinoAluno(idAluno, idTreino);
    }
}
