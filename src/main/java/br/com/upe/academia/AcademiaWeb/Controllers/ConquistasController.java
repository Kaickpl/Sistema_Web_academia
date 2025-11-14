package br.com.upe.academia.AcademiaWeb.Controllers;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistaResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ConquistasDTOs;
import br.com.upe.academia.AcademiaWeb.Services.ConquistasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conquistas")
public class ConquistasController {
    @Autowired
    private ConquistasService conquistasService;

    @GetMapping("/{alunoId}")
    public List<ConquistaResponseDTO> listarConquistas(@PathVariable UUID alunoId) {
        return conquistasService.mostrarConquistas(alunoId);
    }

    @GetMapping("/recente/{alunoId}")
    public ConquistaResponseDTO mostrarUltimaConquista(@PathVariable UUID alunoId){
        return conquistasService.mostrarUltimaConquista(alunoId);
    }

    @PostMapping
    public ResponseEntity<ConquistasDTOs> registrarConquistas(@RequestBody ConquistaRegistroDTO registroDTO){
        Conquistas novaConquista = conquistasService.registrarConquista(registroDTO);
        ConquistasDTOs dto = new ConquistasDTOs(novaConquista);
        return ResponseEntity.ok(dto);
    }
}
