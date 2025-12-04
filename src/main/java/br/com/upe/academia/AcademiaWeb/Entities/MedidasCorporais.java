package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime data;
    @PrePersist
    protected void onCreate() {
        if (data == null) {
            data = LocalDateTime.now();
        }
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
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
