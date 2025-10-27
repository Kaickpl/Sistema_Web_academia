package br.com.upe.academia.AcademiaWeb.Entities;


import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


}
