package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Avaliacao {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID idAvaliacao;
    private LocalDate dataSolicitacao;
    @PrePersist
    protected void onCreate() {
        if (dataSolicitacao == null) {
            dataSolicitacao = LocalDate.now();
        }
    }
    private LocalDate dataAvaliacao;
    private String objetivoAvaliacao;


    @ManyToOne
    @JoinColumn(name = "idMedidas", nullable = true)
    private MedidasCorporais medidasCorporais;

    @ManyToOne
    @JoinColumn(name = "idPersonal")
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "idaluno")
    private Aluno aluno;


}
