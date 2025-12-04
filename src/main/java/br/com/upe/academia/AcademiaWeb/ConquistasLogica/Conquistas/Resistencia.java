package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class Resistencia implements ConquistasInterface {
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO registro = new ConquistaRegistroDTO();
        registro.setAlunoId(alunoId);
        registro.setNomeConquista("Resistência");
        registro.setDescricaoConquista("Você conseguiu fazer mais de 20 repetições em um exercício");
        registro.setMoedas(20);
        return registro;
    }
}
