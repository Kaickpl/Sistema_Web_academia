package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public class Atingiu40quilos implements ConquistasInterface {
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO conquista = new ConquistaRegistroDTO();
        conquista.setAlunoId(alunoId);
        conquista.setNomeConquista("Atingiu 40 quilinhos");
        conquista.setDescricaoConquista("arraso");
        conquista.setMoedas(40);
        return conquista;
    }
}
