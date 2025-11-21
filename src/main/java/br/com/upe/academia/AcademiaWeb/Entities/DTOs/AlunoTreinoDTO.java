package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.UUID;

@Data
public class AlunoTreinoDTO {
    @NonNull
    private UUID idAluno;
    @NonNull
    private UUID idTreino;
    private boolean isCopiaCompartilhada = false;

}
