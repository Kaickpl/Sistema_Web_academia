package br.com.upe.academia.AcademiaWeb.Entities;


import br.com.upe.academia.AcademiaWeb.Entities.Enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario{
    private double saldoMoedas;

    @ManyToMany(mappedBy = "alunos")
    private List<Grupo> grupos = new ArrayList<>();

    public void setTipo(Tipo tipo) {
        this.setTipo(tipo);
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public double getSaldoMoedas() {
        return saldoMoedas;
    }

    public void setSaldoMoedas(double saldoMoedas) {
        this.saldoMoedas = saldoMoedas;
    }
}
