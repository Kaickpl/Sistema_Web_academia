package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, UUID> {
}
