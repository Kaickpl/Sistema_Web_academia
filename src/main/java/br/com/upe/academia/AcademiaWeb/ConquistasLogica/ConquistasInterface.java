package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;

import java.util.UUID;

public interface ConquistasInterface {
    ConquistaRegistroDTO concederConquista(UUID alunoId);
}
