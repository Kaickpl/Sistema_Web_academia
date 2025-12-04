package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class ConquistasExercicios implements ConquistasInterface {
    private String nome;
    private double peso;
    private double valor;
    public ConquistasExercicios(String nome, double peso, double valor){
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
    }
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO registro = new ConquistaRegistroDTO();
        registro.setAlunoId(alunoId);
        registro.setNomeConquista("Levantou " + this.peso+ " quilos no exercício: " + this.nome);
        registro.setDescricaoConquista("Você conseguiu levantar " + this.peso + " quilos no exercício " + this.nome);
        registro.setMoedas((int) Math.round(this.valor * peso));
        return registro;
    }
}
