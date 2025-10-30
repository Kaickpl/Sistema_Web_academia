package br.com.upe.academia.AcademiaWeb.Entities;

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

public class MedidasCorporais {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID idMedidas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @OneToMany(mappedBy = "medidasCorporais", cascade = CascadeType.REMOVE)
    private List<Avaliacao> avaliacoes;

    private Double braco;
    private Double abdomen;
    private Double cintura;
    private Double peito;
    private Double quadril;
    private Double coxa;
    private Double ombro;
    private Double massaMagra;
    private Double gordura;
    private Double percentualAgua;
    private Double peso;
    private Double altura;

}
