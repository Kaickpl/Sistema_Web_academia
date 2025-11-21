package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {
    @Query("SELECT e FROM Exercicio e JOIN e.regrasDeTreinos regra WHERE regra.treino.idTreino = :treinoId")
    List<Exercicio> findByTreinoIdTreino(@Param("treinoId") UUID idTreino);
}
