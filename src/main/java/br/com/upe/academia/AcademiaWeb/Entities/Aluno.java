package br.com.upe.academia.AcademiaWeb.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario{
    private double saldoMoedas;

    @ManyToOne
    @JoinColumn(name = "id_Grupo")
    private Grupo grupo;

    @ManyToMany(mappedBy = "ListaAlunos")
    private List<Grupo> ListaGrupo;

}
