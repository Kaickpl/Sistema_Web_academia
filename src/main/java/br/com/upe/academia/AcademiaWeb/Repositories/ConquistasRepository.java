package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ConquistasRepository extends JpaRepository<Conquistas, UUID> {
    List<Conquistas> findByAluno_IdUsuario(UUID idAluno);
}
