package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.MedidasCorporaisDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MedidasCorporaisService {
    public List<MedidasCorporais> mostrarMedidasCorporais(UUID alunoId);
    public MedidasCorporais registrarMedidas(MedidasCorporaisDTOs medidasCorporaisDTOs);
}
