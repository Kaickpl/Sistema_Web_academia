package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
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

    private LocalDate data;
    @PrePersist
    protected void onCreate() {
        if (data == null) {
            data = LocalDate.now();
        }
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    private Double braco;
    @Column(nullable = false)
    private Double abdomen;
    @Column(nullable = false)
    private Double cintura;
    @Column(nullable = false)
    private Double peito;
    @Column(nullable = false)
    private Double quadril;
    @Column(nullable = false)
    private Double coxa;
    @Column(nullable = false)
    private Double ombro;
    @Column(nullable = false)
    private Double massaMagra;
    @Column(nullable = false)
    private Double gordura;
    @Column(nullable = false)
    private Double percentualAgua;
    @Column(nullable = false)
    private Double peso;
    @Column(nullable = false)
    private Double altura;

}
