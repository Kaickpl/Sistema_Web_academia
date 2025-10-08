package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;

import java.util.List;
import java.util.UUID;

public interface SerieService {
    public Serie adicionarSerie(Serie serie);
    public Serie atualizarSerie(Serie serie);
    public void removerSerie(UUID uuid);
    public Serie buscarSerie(UUID uuid);
    public List<Serie> buscarSeriePorExercicio(Exercicio exercicio);
}
