package br.com.upe.academia.AcademiaWeb.Repositories;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedidasCorporaisRepository extends JpaRepository<MedidasCorporais, UUID> {

    List<MedidasCorporais> findByAluno_IdUsuarioOrderByDataAsc(UUID alunoId);
    MedidasCorporais findTop1ByAluno_IdUsuarioOrderByDataDesc(UUID alunoId);
    MedidasCorporais findByIdMedidas(UUID idMedidas);

}
