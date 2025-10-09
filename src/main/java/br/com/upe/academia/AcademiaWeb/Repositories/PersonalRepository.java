package br.com.upe.academia.AcademiaWeb.Repositories;


import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PersonalRepository extends JpaRepository<Personal, UUID> {

    Optional<Personal> findByCref(String cref);
    Optional<Personal> findByEmail(String email);
    List<Personal> findByNomeUsuarioContaining(String nomeUsuario);
    Optional<Personal> findByNomeUsuario(String nomeUsuario);
    void deleteByCref(String cref);
    Boolean existsByCref(String cref);

}
