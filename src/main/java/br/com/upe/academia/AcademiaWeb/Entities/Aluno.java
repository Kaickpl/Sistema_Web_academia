package br.com.upe.academia.AcademiaWeb.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
