package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaResponseDTO;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/conquistas")
public class ConquistasController {
    @Autowired
    private ConquistasService conquistasService;

    @GetMapping("/{alunoId}")
    public List<ConquistaResponseDTO> listarConquistas(@PathVariable UUID alunoId) {
        return conquistasService.mostrarConquistas(alunoId);
    }

    @GetMapping("/{alunoId}/recente")
    public ConquistaResponseDTO mostrarUltimaConquista(@PathVariable UUID alunoId){
        return conquistasService.mostrarUltimaConquista(alunoId);
    }
}
