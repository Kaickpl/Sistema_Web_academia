package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ObjetivosRepository extends JpaRepository<Objetivos, UUID> {
    List<Objetivos> findAllByAluno_IdUsuario(UUID alunoIdUsuario);

    List<Objetivos> findAllByAluno_IdUsuarioAndConcluido(UUID alunoIdUsuario, boolean concluido);

    List<Objetivos> findAllByAluno_IdUsuarioAndTipoMedidaAndConcluido(UUID alunoIdUsuario, String tipoMedida, boolean concluido);

    boolean existsByIdObjetivo(UUID idObjetivo);
}
