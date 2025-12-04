package br.com.upe.academia.AcademiaWeb.Repositories;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SerieSessaoRepository extends JpaRepository<SerieSessao, UUID> {
    @Query("""
        SELECT s
        FROM SerieSessao s 
        JOIN s.exercicioSessao es
        JOIN es.treinoExecucao ts
        WHERE es.exercicioTemplate.idExercicio = :idTemplate
        AND ts.aluno.idUsuario = :idAluno
        ORDER BY s.peso DESC, ts.dataExecucao DESC
""")
    List<SerieSessao> findRecordeDeCarga(
            @Param("idTemplate")UUID idExercicioTemplate,
            @Param("idAluno") UUID idAluno,
            Pageable pageable);
}
