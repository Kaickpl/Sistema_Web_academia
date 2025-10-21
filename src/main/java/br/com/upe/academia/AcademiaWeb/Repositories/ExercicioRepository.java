package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {
    List<Exercicio> findByTreinoIdTreino(UUID idTreino);
}
