package br.com.upe.academia.AcademiaWeb.Entities;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario{
    @Column(nullable = false)
    private int saldoMoedas;

    @ManyToMany(mappedBy = "alunos")
    //aqui
    private List<Grupo> grupos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "aluno_treinos",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private List<Treino> treinosAtribuidos = new ArrayList<>();

}
