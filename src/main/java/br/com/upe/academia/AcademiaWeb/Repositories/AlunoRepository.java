package br.com.upe.academia.AcademiaWeb.Repositories;


import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Aluno findByEmail(String email);
//    Aluno findByGrupo(Grupo grupo);
    // perguntar
    Aluno findByNomeUsuario(String NomeUsuario);
    Aluno findByIdUsuario(UUID idUsuario);
    List<Aluno> findAllByGrupo(Grupo grupo);
    Aluno findByIdAvaliacao(UUID IdAvaliacao);

}
