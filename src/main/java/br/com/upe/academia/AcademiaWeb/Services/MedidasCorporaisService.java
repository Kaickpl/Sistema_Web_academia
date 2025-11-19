package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MedidasCorporaisService {
    public List<MedidasCorporaisResponseDTO> mostrarHistoricoMedidasCorporais(UUID alunoId);
    public MedidasCorporaisResponseDTO mostrarMedidasAtuais(UUID alunoId);
    public MedidasCorporais registrarMedidas(MedidasCorporaisRegistroDTO medidasCorporaisDTOs);

    Double buscarUltimoValorMedida(UUID alunoId, String tipoMedida);
}
