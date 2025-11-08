package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
public class AlunoTreinoDTO {
    @NonNull
    UUID idAluno;
    @NonNull
    UUID idTreino;

}
