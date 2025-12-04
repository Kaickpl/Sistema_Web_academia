package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MedidasCorporaisService {
    List<MedidasCorporaisResponseDTO> mostrarHistoricoMedidasCorporais(UUID alunoId);
    MedidasCorporaisResponseDTO mostrarMedidasAtuais(UUID alunoId);
    MedidasCorporais registrarMedidas(MedidasCorporaisRegistroDTO medidasCorporaisDTOs);
    MedidasCorporais buscarMedidasPorId(UUID idMedidas);

    Double buscarUltimoValorMedida(UUID alunoId, String tipoMedida);

    List<MedidasCorporaisResponseDTO> mostrar10Medidas(UUID alunoId);
}
