package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.MedidasCorporais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public interface AnaliseDesempenhoService {

    void processarDesempenhoSerie(SessaoProgressaoResponseDTO progressoDTO);

    void processarNovasMedidas(MedidasCorporais novaMedida);
}
