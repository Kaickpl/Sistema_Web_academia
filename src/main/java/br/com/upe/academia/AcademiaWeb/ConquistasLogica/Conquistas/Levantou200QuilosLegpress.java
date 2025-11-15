package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;

import java.util.UUID;

public class Levantou200QuilosLegpress implements ConquistasInterface {
    ConquistasService service;
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO registro = new ConquistaRegistroDTO();
        registro.setAlunoId(alunoId);
        registro.setNomeConquista("Levantou duzentos quilos no legpress");
        registro.setDescricaoConquista("Você conseguiu levantar duzentos quilos no legpress");
        registro.setMoedas(200);
        return registro;
    }
}
