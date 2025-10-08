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


    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public UUID getIdAvaliacao() {
        return IdAvaliacao;
    }

    public void setIdAvaliacao(UUID idAvaliacao) {
        IdAvaliacao = idAvaliacao;
    }

    public br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais getMedidasCorporais() {
        return MedidasCorporais;
    }

    public void setMedidasCorporais(br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais medidasCorporais) {
        MedidasCorporais = medidasCorporais;
    }

    public String getObjeitvoAvaliacao() {
        return objeitvoAvaliacao;
    }

    public void setObjeitvoAvaliacao(String objeitvoAvaliacao) {
        this.objeitvoAvaliacao = objeitvoAvaliacao;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
