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
public class Conquistas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConquistas;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    private Date dataConquista;
    @Column(length = 512)
    private String objetivo;

    public Date getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(Date dataConquista) {
        this.dataConquista = dataConquista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
}
