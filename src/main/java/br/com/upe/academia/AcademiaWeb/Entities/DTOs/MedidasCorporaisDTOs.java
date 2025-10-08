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
    private UUID idMedidas;
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
        this.idMedidas = medidasCorporais.getIdMedidas();
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

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getBraco() {
        return braco;
    }

    public void setBraco(Double braco) {
        this.braco = braco;
    }

    public Double getCintura() {
        return cintura;
    }

    public void setCintura(Double cintura) {
        this.cintura = cintura;
    }

    public Double getCoxa() {
        return coxa;
    }

    public void setCoxa(Double coxa) {
        this.coxa = coxa;
    }

    public UUID getIdMedidas() {
        return idMedidas;
    }

    public void setIdMedidas(UUID idMedidas) {
        this.idMedidas = idMedidas;
    }

    public Double getGordura() {
        return gordura;
    }

    public void setGordura(Double gordura) {
        this.gordura = gordura;
    }

    public Double getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(Double massaMagra) {
        this.massaMagra = massaMagra;
    }

    public Double getPeito() {
        return peito;
    }

    public void setPeito(Double peito) {
        this.peito = peito;
    }

    public Double getOmbro() {
        return ombro;
    }

    public void setOmbro(Double ombro) {
        this.ombro = ombro;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPercentualAgua() {
        return percentualAgua;
    }

    public void setPercentualAgua(Double percentualAgua) {
        this.percentualAgua = percentualAgua;
    }

    public Double getQuadril() {
        return quadril;
    }

    public void setQuadril(Double quadril) {
        this.quadril = quadril;
    }
}
