package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class Volume500 implements ConquistasInterface {

    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO registro = new ConquistaRegistroDTO();
        registro.setAlunoId(alunoId);
        registro.setNomeConquista("Volume de 500");
        registro.setDescricaoConquista("Você conseguiu o volume de 500 quilos em qualquer exercicio.");
        registro.setMoedas(50);
        return registro;
    }
}
