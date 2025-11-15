package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class Levantou50QuilosSupinoInclinado implements ConquistasInterface{
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO registro = new ConquistaRegistroDTO();
        registro.setNomeConquista("Levantou 50 quilos no supino");
        registro.setDescricaoConquista("Você conseguiu levantar 50 quilos no supino!");
        registro.setMoedas(90);
        registro.setAlunoId(alunoId);
        return registro;
    }
}
