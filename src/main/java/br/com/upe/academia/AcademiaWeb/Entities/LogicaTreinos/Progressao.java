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

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    @Column(nullable = false)
    private String nomeExercicio;
    @Column(nullable = false)
    private int peso;
    @Column(nullable = false)
    private int repeticoes;

    private LocalDate data;

    public Progressao(Aluno aluno, String nomeExercicio, int peso, int repeticoes) {
        this.aluno = aluno;
        this.nomeExercicio = nomeExercicio;
        this.peso = peso;
        this.repeticoes = repeticoes;
    }

    @PrePersist
    protected void onCreate() {
        if (data == null) {
            data = LocalDate.now();
        }
    }
}
