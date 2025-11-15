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
@NoArgsConstructor
@AllArgsConstructor
public class Objetivos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idObjetivo;
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    @Column(nullable = false)
    private String tipoMedida;
    @Column(nullable = false)
    private double valorAlvo;
    @Column(nullable = false)
    private String condicao;
    @Column(nullable = false)
    private String status;
}
