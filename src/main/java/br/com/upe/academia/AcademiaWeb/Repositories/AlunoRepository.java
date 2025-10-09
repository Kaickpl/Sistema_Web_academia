package br.com.upe.academia.AcademiaWeb.Repositories;


import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    Optional<Aluno> findByEmail(String email);
    List<Aluno> findByNomeUsuarioContaining(String NomeUsuario);
    @Query("SELECT a FROM Aluno a JOIN a.grupos g WHERE g = :grupo")
    List<Aluno> findByGrupo(@Param("grupo") Grupo grupo);
}

