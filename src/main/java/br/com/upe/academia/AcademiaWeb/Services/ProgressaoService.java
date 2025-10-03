package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Progressao;
import br.com.upe.academia.AcademiaWeb.Repositories.ProgressaoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProgressaoService {
    Progressao mostrarProgressao();
}
