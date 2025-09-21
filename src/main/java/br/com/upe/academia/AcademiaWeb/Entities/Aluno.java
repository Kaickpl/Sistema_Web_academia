package br.com.upe.academia.AcademiaWeb.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Aluno extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IDAluno;
    private double moedas;


}
