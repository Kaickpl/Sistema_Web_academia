package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conquistas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConquistas;
    private String nomeConquista;
    private String descricaoConquista;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    public Conquistas(Aluno aluno, String descricao, String nome) {
        this.aluno = aluno;
        this.descricaoConquista = descricao;
        this.nomeConquista = nome;
    }

    private LocalDate dataConquista;


    @PrePersist
    protected void onCreate() {
        if (dataConquista == null) {
            dataConquista = LocalDate.now();
        }
    }

}
