package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTreino;
    private Duration duracao;
    private String nome;
    private boolean isConcluido = false;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL)
    private List<Exercicio> exercicio;

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public List<Exercicio> getExercicio() {
        return exercicio;
    }

    public void setExercicio(List<Exercicio> exercicio) {
        this.exercicio = exercicio;
    }

    public UUID getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(UUID idTreino) {
        this.idTreino = idTreino;
    }

    public boolean isConcluido() {
        return isConcluido;
    }

    public void setConcluido(boolean concluido) {
        isConcluido = concluido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
