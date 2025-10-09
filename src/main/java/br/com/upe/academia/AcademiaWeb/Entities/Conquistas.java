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
public class Conquistas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConquistas;

    private String nomeConquista;
    private String descricaoConquista;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    public Conquistas(Aluno aluno, String descricao, String nome) {
        this.aluno = aluno;
        this.descricaoConquista = descricao;
        this.nomeConquista = nome;
    }
    public Conquistas() {}

    private LocalDate dataConquista;


    @PrePersist
    protected void onCreate() {
        if (dataConquista == null) {
            dataConquista = LocalDate.now();
        }
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }

    public String getDescricaoConquista() {
        return descricaoConquista;
    }

    public void setDescricaoConquista(String descricaoConquista) {
        this.descricaoConquista = descricaoConquista;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public UUID getIdConquistas() {
        return idConquistas;
    }

    public void setIdConquistas(UUID idConquistas) {
        this.idConquistas = idConquistas;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }

}
