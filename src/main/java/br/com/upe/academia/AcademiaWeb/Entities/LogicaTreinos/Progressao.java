package br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Progressão")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Progressao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProgressao;

    private UUID alunoId;

    private String nomeExercicio;

    private int peso;

    private LocalDate data;

    @PrePersist
    protected void onCreate() {
        if (data == null) {
            data = LocalDate.now();
        }
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public UUID getAlunoId() {
        return alunoId;
    }

    public UUID getIdProgressao() {
        return idProgressao;
    }

    public void setIdProgressao(UUID idProgressao) {
        this.idProgressao = idProgressao;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
