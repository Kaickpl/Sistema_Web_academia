package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, UUID> {

    @Modifying
    @Query(value = "DELETE FROM aluno_treinos WHERE treino_id = :idTreino", nativeQuery = true)
    void desvincularTreinoDeTodosAlunos(@Param("idTreino") UUID idTreino);
}
