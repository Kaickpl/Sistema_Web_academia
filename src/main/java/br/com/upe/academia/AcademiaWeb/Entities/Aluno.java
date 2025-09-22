package br.com.upe.academia.AcademiaWeb.Entities;


import br.com.upe.academia.AcademiaWeb.Entities.Enums.tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Aluno extends Usuario{
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID IDAluno;
    private double saldoMoedas;
    @ManyToOne
    @JoinTable(
            name = "Monitoramento",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_personal")
    )
    private Personal personal;
}
