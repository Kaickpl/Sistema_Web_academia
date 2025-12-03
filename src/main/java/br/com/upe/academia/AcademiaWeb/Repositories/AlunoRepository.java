package br.com.upe.academia.AcademiaWeb.Repositories;


import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Aluno findByIdUsuario(UUID idUsuario);
    Optional<Aluno> findByEmail(String email);
    List<Aluno> findByNomeUsuarioContaining(String NomeUsuario);
}

