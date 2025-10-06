package br.com.upe.academia.AcademiaWeb.Repositories;


import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

    List<Grupo> findByPersonal(Personal personal);
    Grupo findGrupoByNomeGrupo(String Grupo);

}
