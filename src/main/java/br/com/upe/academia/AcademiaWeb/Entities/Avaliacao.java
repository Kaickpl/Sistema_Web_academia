package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Avaliacao {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID IdAvaliacao;
    private Date dataSolicitacao;
    private Date dataAvaliacao;
    private String objeitvoAvaliacao;


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
