package br.com.upe.academia.AcademiaWeb.ConquistasLogica;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
public class Contexto {
    private ConquistasInterface tipo;
    private final ConquistasService service;
    public Contexto(@Qualifier("conquistasServiceImpl") ConquistasService service) {
        this.service = service;
    }
    public void registrar(UUID alunoId) {
        ConquistaRegistroDTO dto = tipo.concederConquista(alunoId);
        service.registrarConquista(dto);
    }
}
