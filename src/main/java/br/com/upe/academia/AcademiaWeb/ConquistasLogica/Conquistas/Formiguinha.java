package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class Formiguinha implements ConquistasInterface {
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO conquista = new ConquistaRegistroDTO();
        conquista.setAlunoId(alunoId);
        conquista.setNomeConquista("Formiguinha");
        conquista.setDescricaoConquista("Você conseguiu levantar três vezes o seu peso em qualquer exercício");
        conquista.setMoedas(35);
        return conquista;
    }
}
