package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SolicitacaoAvaliacao {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID IdAvaliacao;
    private Double peso;
    private Double altura;
    private Date dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "idMedidas")
    private MedidasCorporais MedidasCorporais;

    @ManyToOne
    @JoinColumn(name = "idPersonal")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "idaluno")
    private Aluno aluno;




}
