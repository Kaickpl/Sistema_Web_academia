package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.UUID;

@Data
public class AlunoTreinoDTO {
    private UUID idAluno;
    @NonNull
    private UUID idTreino;
    @JsonProperty("isCopiaCompartilhada")
    private boolean isCopiaCompartilhada = false;

}
