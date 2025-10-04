package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import org.springframework.stereotype.Service;

@Service
public interface ProgressaoService {
    Progressao mostrarProgressao();
}
