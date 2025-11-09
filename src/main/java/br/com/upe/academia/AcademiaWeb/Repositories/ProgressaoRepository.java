package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoResponseDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProgressaoRepository extends JpaRepository<Progressao, UUID> {
    List<Progressao> findByAluno_IdUsuarioAndNomeExercicioOrderByDataAsc(UUID idUsuario, String nomeExercicio);
}
