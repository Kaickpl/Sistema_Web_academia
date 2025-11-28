package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio, UUID> {
}
