package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessaoProgressaoResponseMapper {
    public SessaoProgressaoResponseDTO toDTO(UUID idSessaoProgressao) {
        SessaoProgressaoResponseDTO dto = new SessaoProgressaoResponseDTO(idSessaoProgressao);
        return dto;
    }
}
