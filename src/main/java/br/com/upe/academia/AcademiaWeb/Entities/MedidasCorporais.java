package br.com.upe.academia.AcademiaWeb.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class MedidasCorporais {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
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

    public Double getCintura() {
        return cintura;
    }

    public void setCintura(Double cintura) {
        this.cintura = cintura;
    }

    public Double getBraco() {
        return braco;
    }

    public void setBraco(Double braco) {
        this.braco = braco;
    }

    public Double getCoxa() {
        return coxa;
    }

    public void setCoxa(Double coxa) {
        this.coxa = coxa;
    }

    public Double getGordura() {
        return gordura;
    }

    public void setGordura(Double gordura) {
        this.gordura = gordura;
    }

    public UUID getIdMedidas() {
        return idMedidas;
    }

    public void setIdMedidas(UUID idMedidas) {
        this.idMedidas = idMedidas;
    }

    public Double getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(Double massaMagra) {
        this.massaMagra = massaMagra;
    }

    public Double getOmbro() {
        return ombro;
    }

    public void setOmbro(Double ombro) {
        this.ombro = ombro;
    }

    public Double getPeito() {
        return peito;
    }

    public void setPeito(Double peito) {
        this.peito = peito;
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
