package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PersonalRepository extends JpaRepository<Personal, UUID> {
    Personal findByCref(String cref);
    Personal findByEmail(String email);
    // perguntar
    Personal findByIdUsuario(UUID idUsuario);
    Personal findByNomeUsuario(String nomeUsuario);
    Personal findBynomeGrupo(String nomeGrupo);
}
