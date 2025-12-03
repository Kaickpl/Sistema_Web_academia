package br.com.upe.academia.AcademiaWeb.ConquistasLogica.Conquistas;

import br.com.upe.academia.AcademiaWeb.ConquistasLogica.ConquistasInterface;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import lombok.Setter;

import java.util.UUID;
@Setter
public class AtingiuObjetivo implements ConquistasInterface {
    UUID alunoId;
    String nomeConquista;
    String descricaoConquista;
    public AtingiuObjetivo (UUID alunoId, String tipo){
        this.alunoId = alunoId;
        this.nomeConquista = "Objetivo atingido: " + tipo;
        this.descricaoConquista = "Você conseguiu atingir seu objetivo pessoal de " + tipo;
    }

    @Override
    public ConquistaRegistroDTO concederConquista(UUID alunoId) {
        ConquistaRegistroDTO conquista = new ConquistaRegistroDTO();
        conquista.setAlunoId(alunoId);
        conquista.setNomeConquista(this.nomeConquista);
        conquista.setDescricaoConquista(this.descricaoConquista);
        conquista.setMoedas(50);
        return conquista;
    }


}
