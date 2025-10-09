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

    private LocalDate dataConquista;
    @Column(columnDefinition = "int default 0", nullable = false)
    private int moedas;


    @PrePersist
    protected void onCreate() {
        if (dataConquista == null) {
            dataConquista = LocalDate.now();
        }
    }
    public Conquistas(Aluno aluno, String titulo, String descricao, int moedas) {
        this.aluno = aluno;
        this.nomeConquista = titulo;
        this.descricaoConquista = descricao;
        this.moedas = moedas;
    }
}
