package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ConquistasService {
    public void concederConquistas(UUID id_usuario);
    public Page<Conquistas> mostrarQuadroDeConquistas(Pageable page);
}
