package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ConquistasServiceImpl implements ConquistasService {
    @Override
    public void concederConquistas(UUID id_usuario) {

    }

    @Override
    public Page<Conquistas> mostrarQuadroDeConquistas(Pageable page) {
        return null;
    }
}
