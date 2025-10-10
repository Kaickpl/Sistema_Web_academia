package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.utils.DurationDeserializer;
import br.com.upe.academia.AcademiaWeb.utils.DurationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TreinoDTOs {
    private UUID idTreino;
    @JsonDeserialize(using = DurationDeserializer.class)
    @JsonSerialize(using = DurationSerializer.class)
    private Duration duracao;
    private String nome;
    private boolean isConcluido = false;

    public TreinoDTOs(Treino treino){
        this.idTreino = treino.getIdTreino();
        this.duracao = treino.getDuracao();
        this.nome = treino.getNome();
        this.isConcluido = treino.isConcluido();
    }
}
