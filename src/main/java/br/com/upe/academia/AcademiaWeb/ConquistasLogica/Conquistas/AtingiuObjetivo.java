package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import lombok.Setter;

import java.util.UUID;
@Setter
public class AtingiuObjetivo implements ConquistasInterface {
    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO conquista = new ConquistaRegistroDTO();
        conquista.setAlunoId(alunoId);
        conquista.setNomeConquista("Atingiu seu objetivo");
        conquista.setDescricaoConquista("Você conseguiu atingir seu objetivo pessoal");
        conquista.setMoedas(50);
        return conquista;
    }
}
