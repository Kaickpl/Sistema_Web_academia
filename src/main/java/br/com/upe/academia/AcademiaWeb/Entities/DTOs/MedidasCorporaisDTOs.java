package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedidasCorporaisDTOs {
    private UUID alunoId;
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

    public MedidasCorporaisDTOs(MedidasCorporais medidasCorporais) {
        this.alunoId = medidasCorporais.getAluno().getIdUsuario();
        this.braco = medidasCorporais.getBraco();
        this.abdomen = medidasCorporais.getAbdomen();
        this.cintura = medidasCorporais.getCintura();
        this.peito = medidasCorporais.getPeito();
        this.quadril = medidasCorporais.getQuadril();
        this.coxa = medidasCorporais.getCoxa();
        this.ombro = medidasCorporais.getOmbro();
        this.massaMagra = medidasCorporais.getMassaMagra();
        this.gordura = medidasCorporais.getGordura();
        this.percentualAgua = medidasCorporais.getPercentualAgua();
        this.peso = medidasCorporais.getPeso();
        this.altura = medidasCorporais.getAltura();

    }
}
