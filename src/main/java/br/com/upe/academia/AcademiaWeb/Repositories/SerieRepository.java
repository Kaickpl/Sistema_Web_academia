package br.com.upe.academia.AcademiaWeb.Repositories;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SerieRepository extends JpaRepository<Serie, UUID> {
    List<Serie> findByTreinoExercicio(TreinoExercicio treinoExercicio);

}
